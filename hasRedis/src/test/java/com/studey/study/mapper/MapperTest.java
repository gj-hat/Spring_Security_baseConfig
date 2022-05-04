package com.studey.study.mapper;

import com.study.web.entity.UserDomain;
import com.study.web.mapper.SysMenuMapper;
import com.study.web.mapper.UserMapper;
import com.study.web.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    public void TestBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("321321");
        System.out.println("encode = " + encode);

    }

    // userService
    @Test
    public void test1() {
        List<UserDomain> userDomains = userService.list();
        System.out.println("userDomains = " + userDomains);
    }


    @Test
    public void test() {
        List<UserDomain> userDomains = userMapper.selectList(null);
        System.out.println("userDomains = " + userDomains);
    }



    @Test
    public void testSysMenuMapper(){
        List<String> strings = sysMenuMapper.selectPermsByUserId(2L);
        System.out.println("strings = " + strings);

    }





}



