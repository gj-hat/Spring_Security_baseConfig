package com.security.study.web.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.study.web.entity.UserDomain;
import com.security.study.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// myBatis-plus
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDomain> {


    @Autowired
    private UserMapper userMapper;

    public UserDomain findUserByUsername(String username) {
        return null;
    }
}








