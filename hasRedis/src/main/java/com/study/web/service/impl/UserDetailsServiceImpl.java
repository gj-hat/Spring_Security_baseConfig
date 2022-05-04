package com.study.web.service.impl;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.study.web.entity.UserDomain;
import com.study.web.mapper.UserMapper;
import com.study.web.entity.LoginUser;
import com.study.web.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /*
     * 这个方法
     * 只查询用户名是否存在  由SpringSecurity自动调用 方法将用户信息封装到UserDetails中 进行下一步用户名密码认证
     *
     * 根据流程图
     * DaoAuthenticationProvider会自动调用 loadUserByUsername 方法查询用户名和密码  还有权限等的信息 是否匹配
     *
     *
     *
     *
     *
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //  根据用户名  查询用户名密码等信息
        UserDomain userDomain =
                new LambdaQueryChainWrapper<>(userMapper)
                .eq(UserDomain::getUserName, username)
                .one();

        // 如果没有查询到用户 抛出异常
        if (userDomain == null) {
            throw new UsernameNotFoundException("根据用户名没有查到用户");
        }


        // 多表查询用户的权限 信息
        List<String> userPerms = sysMenuMapper.selectPermsByUserId(userDomain.getId());


        // 数据封装为UserDetails
        return new LoginUser(userDomain, userPerms);
    }
}
