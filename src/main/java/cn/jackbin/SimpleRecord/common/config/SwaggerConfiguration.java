package cn.jackbin.SimpleRecord.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: create by bin
 * @version: v1.0
 * @description: cn.jackbin.SimpleRecord.common.config
 * @date: 2020/9/1 19:08
 **/
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("简账接口文档")
                        .description("服务相关接口")
                        .version("1.0")
                        .contact(new Contact()
                                .name("bin")
                                .email("itsbintnt@163.com")));
    }
}
