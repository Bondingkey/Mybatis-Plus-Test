package com.gzc.mp.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/11/25  17:05  周六
 * @Project: Mybatis-Plus-Test
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@Component
public class MyFill implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {//添加时执行
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("version",1,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {//修改时执行
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
