package com.study.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.web.entity.LoginUser;
import com.study.web.entity.UserDomain;
import com.study.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/7 21:57
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    /**
     *  根据流程图
     *  DaoAuthenticationProvider会自动调用 loadUserByUsername 方法查询用户名和密码  还有权限等的信息 是否匹配
     *
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        LambdaQueryWrapper<UserDomain> user = new LambdaQueryWrapper<>();
        user.eq(UserDomain::getUserName, username);
        UserDomain userDomain = userMapper.selectOne(user);
        // 如果没有查询到用户 抛出异常
        if (userDomain == null) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }




        // 数据封装为UserDetails
        return new LoginUser(userDomain);
    }
}
