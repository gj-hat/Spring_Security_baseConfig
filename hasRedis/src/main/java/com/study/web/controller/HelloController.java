package com.study.web.controller;

import com.study.web.entity.UserDomain;
import com.study.web.entity.dto.ResponseResult;
import com.study.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/7 20:23
 * @description：helloController
 * @modified By：
 * @version: 1.0
 */
@RestController
public class HelloController {


    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/hello")
    public ResponseResult hello() {
        return new ResponseResult(200,"hello world");

    }


    @RequestMapping("/hello1")
    // 有test权限的用户才能访问
    @PreAuthorize("hasAuthority('system:dept:list')")
    public ResponseResult hello1() {
        return new ResponseResult(200, "带有test权限的用户才能访问");
    }


    @RequestMapping("/hello11")
    // 无权限的用户才能访问
    @PreAuthorize("hasAuthority('system:dept:list11')")
    public ResponseResult hello11() {
        return new ResponseResult(200, "这是无权限的");
    }







    //    findAll
    @RequestMapping("/list")
    public List<UserDomain> list() {
        return userService.list();
    }


}