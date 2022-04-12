package com.study.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/7 22:12
 * @description：UserDerails对象实现
 * @modified By：
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    /**
     *  根据流程图
     *  DaoAuthenticationProvider会自动调用 loadUserByUsername 方法查询用户名和密码  还有权限等的信息
     *
     */


    private UserDomain userDomain;


    // 获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userDomain.getPassword();
    }

    @Override
    public String getUsername() {
        return userDomain.getUserName();
    }


    // 是否没过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    // 账户是否被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;

    }

    // 是否没有超时
    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    // 用户是否可用
    @Override
    public boolean isEnabled() {
        return true;

    }
}
