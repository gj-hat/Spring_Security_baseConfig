package com.study.mapper;

import com.study.web.entity.UserDomain;
import com.study.web.mapper.UserMapper;
import com.study.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/7 21:32
 * @description：测试UserMapper
 * @modified By：
 * @version: 1.0
 */
@SpringBootTest
public class MapperTest {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;

    // userService
    @Test
    public void test1(){
        List<UserDomain> userDomains = userService.list();
        System.out.println("userDomains = " + userDomains);
    }


    @Test
    public void test(){
        List<UserDomain> userDomains = userMapper.selectList(null);
        System.out.println("userDomains = " + userDomains);
    }
}



