package com.study.web.controller;

import com.study.web.entity.UserDomain;
import com.study.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String hello() {
        return "Hello World!";
    }


    //    findAll
    @RequestMapping("/list")
    public List<UserDomain> list() {
        return userService.list();
    }


}
