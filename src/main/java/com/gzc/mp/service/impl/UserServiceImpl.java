package com.gzc.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzc.mp.mapper.UserMapper;
import com.gzc.mp.pojo.User;
import com.gzc.mp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/11/26  16:24  周日
 * @Project: Mybatis-Plus-Test
 * @Write software: IntelliJ IDEA
 * @Purpose: 在此处编辑
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
