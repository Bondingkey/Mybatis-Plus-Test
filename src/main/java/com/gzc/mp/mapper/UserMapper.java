package com.gzc.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzc.mp.pojo.User;

//如果想要使用mp,该mapper接口需要实现BaseMapper<POJO> 类
public interface UserMapper extends BaseMapper<User> {
}
