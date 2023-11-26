package com.gzc.mp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/11/25  09:46  周六
 * @Project: Mybatis-Plus-Test
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@Data
@TableName("`db_user`")//该注解表明当前类与数据库中的那张表对应
public class User {//类中的属性名与表中的字段名需一致才能封装

    @TableId(value = "id",type = IdType.NONE)//该注解表明此属性为表中的主键,值映射表中的主键字段,type指定主键生成策略
    private Long uid;

    @TableField("name")//如果出现表字段名与属性名不一致时用该注解指定映射关系
    private String username;

    private Integer age;

    @TableField(exist = false)//当出现类中的属性比表中的字段多时,可以使用@TableField("name")来指定封装的属性对应,也可以设置不封装@TableField(exist = false)
    private String phonenumber;

    private String email;

    private String aiHao;//mp会自动转换驼峰式命名,且必选转换,不可一致,否则结果封装不进去(也可以关闭驼峰式映射)

    @TableField(fill = FieldFill.INSERT) //fill属性指定自动填充的时机
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version//乐观锁
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    //@TableLogic//逻辑删除,如果yml配置了,就不用加注解了
    private Integer isDeleted;

    public User() {
    }

    public User(Long id, String username, Integer age, String email, String aiHao) {
        this.uid = id;
        this.username = username;
        this.age = age;
        this.email = email;
        this.aiHao = aiHao;
    }
}
