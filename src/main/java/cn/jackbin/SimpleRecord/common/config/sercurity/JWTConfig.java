package cn.jackbin.SimpleRecord.common.config.sercurity;

import cn.jackbin.SimpleRecord.constant.PermissionConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author: create by bin
 * @version: v1.0
 * @description: cn.jackbin.SimpleRecord.common.config
 * @date: 2020/7/22 21:46
 **/
@Component
public class JWTConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private long expire;

    @Value("${jwt.header}")
    private String header;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        // 确保密钥长度符合 HS512 要求（至少 64 字节）
        if (keyBytes.length < 64) {
            byte[] paddedKey = new byte[64];
            System.arraycopy(keyBytes, 0, paddedKey, 0, keyBytes.length);
            return Keys.hmacShaKeyFor(paddedKey);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成Token
     * @param userName
     * @param permissionList
     * @return
     */
    public String createToken (String userName, List<String> permissionList){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000 * 3600);//过期时间
        Map<String,Object> map = new HashMap<>();
        map.put(PermissionConstant.PermissionSign,permissionList);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(map)
                .setSubject(userName)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim (String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token是否过期失效
     * @param claims
     * @return
     */
    public boolean isTokenExpired (Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    /**
     * 获取用户名从token中
     */
    public String getUserIdFromToken(Claims claims) {
        return claims.getSubject();
    }

    /**
     * 获取用户权限列表
     */
    @SuppressWarnings("unchecked")
    public List<String> getPermissions(Claims claims) {
        return (List<String>)claims.get(PermissionConstant.PermissionSign);
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(Claims claims) {
        return claims.getIssuedAt();
    }

    // --------------------- getter & setter ---------------------

    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public long getExpire() {
        return expire;
    }
    public void setExpire(long expire) {
        this.expire = expire;
    }
    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }
}
