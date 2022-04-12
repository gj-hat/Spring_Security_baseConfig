package com.study.web.service.impl;


import com.study.utils.JwtUtil;
import com.study.utils.RedisCache;
import com.study.web.entity.LoginUser;
import com.study.web.entity.UserDomain;
import com.study.web.entity.dto.ResponseResult;
import com.study.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/8 15:31
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;


    @Override
    public ResponseResult login(UserDomain user) {

        // AuthenticationManager进行认证   查询数据库找到用户名和密码

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);


        // authenticate不通给出对应的提示 抛出异常登录失败

        if (authenticate == null) {
            throw new RuntimeException("登录失败");
        }

        // 认证成功，使用userid生成一个jwt
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        String id = principal.getUserDomain().getId().toString();
        String jwt = JwtUtil.createJWT(id);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        // 把完整的用户信息存入redis中 userid作为key
        redisCache.setCacheObject("login:" + id, principal);
        LoginUser cacheMap = redisCache.getCacheObject("login:" + id);
        System.out.println(cacheMap);


        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUserDomain().getId();


        // 删除redis
        redisCache.deleteObject("login:" + id);


        return new ResponseResult(200, "退出成功");
    }
}
