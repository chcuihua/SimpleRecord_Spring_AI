package cn.jackbin.SimpleRecord.common.config.datasource;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

import java.util.List;

/**
 * @author: create by bin
 * @version: v1.0
 * @description: cn.jackbin.SimpleRecord.common.config.datasource
 * @date: 2021/8/5 19:48
 **/
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        // 暂时注释自定义方法，避免编译错误
        // methodList.add(new SelectOneWithoutLogicDel());
        // methodList.add(new LogicDelWithFillStatus());
        // methodList.add(new SelectPageWithoutLogicDel());
        // methodList.add(new UpdateByIdWithoutLogicDel());
        return methodList;
    }
}
