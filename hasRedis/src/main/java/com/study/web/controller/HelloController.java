package com.study.web.controller;

import com.study.web.entity.UserDomain;
import com.study.web.entity.dto.ResponseResult;
import com.study.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    // 用户具有下面权限任意一个即可
//    @PreAuthorize("hasAnyAuthority('admin','system:dept:list11')")
    // 自动进行拼接
//    @PreAuthorize("hasRole('admin')")
    public ResponseResult hello11() {
        return new ResponseResult(200, "这是无权限的");
    }



    // 自定义用户权限  调用自己的权限校验类expressionRoot
    @RequestMapping("/hello2")
    @PreAuthorize("@expressionRoot.hasAuthority('system:dept:list')")
    public ResponseResult hello2() {
        return new ResponseResult(200, "这是自定义权限的");
    }


    // 其实还可以在配置类中进行配置  去看配置类去
    @RequestMapping("/hello3")
    public ResponseResult hello3() {
        return new ResponseResult(200, "这是自定义权限的");
    }



    @GetMapping("/noauth")
    public ResponseResult noauth() {
        return new ResponseResult(200, "不需要权限就可以访问");
    }


    //    findAll
    @RequestMapping("/list")
    public List<UserDomain> list() {
        return userService.list();
    }


}
