package cn.jackbin.SimpleRecord.common.config.datasource;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author: create by bin
 * @version: v1.0
 * @description: 自定义通用mapper
 * @date: 2021/8/5 19:50
 **/
public interface MyBaseMapper<T> extends BaseMapper<T> {

    // 暂时注释自定义方法，避免编译错误
    // T selectOneWithoutLogicDel(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
    // int logicDelByIdFillStatus(Serializable id);
    // int delByIdFillStatus(Serializable id);
    // <E extends IPage<T>> E selectPageWithoutLogicDel(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
    // int updateByIdWithoutLogicDel(@Param(Constants.ENTITY) T entity);
    // int delById(Serializable id);
}
