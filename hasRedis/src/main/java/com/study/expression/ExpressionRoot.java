package com.study.expression;

import com.study.web.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/13 15:00
 * @description：自定义权限校验
 * @modified By：
 * @version: 1.0
 */
// 自定义Bean名字
@Component("expressionRoot")
public class ExpressionRoot {

    public final boolean hasAuthority(String authority) {
        // 获取当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> userPermissions = loginUser.getPermissions();
        // 判断用户权限集合中是否存在Authority
        return userPermissions.contains(authority);

    }


}


