package com.study.web.controller;

import com.study.web.entity.UserDomain;
import com.study.web.service.impl.LoginServiceImpl;
import com.study.web.entity.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/8 15:24
 * @description：登录
 * @modified By：
 * @version: 1.0
 */
@RestController
public class LoginController {


    @Autowired
    private LoginServiceImpl loginService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody UserDomain user) {
        return loginService.login(user);
    }

    // 退出登录
    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

}

