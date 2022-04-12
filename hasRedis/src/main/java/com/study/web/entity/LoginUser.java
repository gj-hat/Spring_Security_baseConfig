package com.study.web.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
public class LoginUser implements UserDetails {

    /**
     * 根据流程图
     * DaoAuthenticationProvider会自动调用 loadUserByUsername 方法查询用户名和密码  还有权限等的信息
     */


    private UserDomain userDomain;

    private List<String> permissions;

    public LoginUser(UserDomain userDomain, List<String> permissions) {
        this.userDomain = userDomain;
        this.permissions = permissions;
    }

    // 写成成员变量的形式 只在第一次的时候调用 查询用户的权限   后面就不用调用了
    // 序列化的时候 忽略  因为spring的规定  SimpleGrantedAuthority类型序列化的时候会报错
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    // 获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }

        // 把permissions中的String权限信息 封装成SimpleGrantedAuthority对象
        /**
         *          封装的方法一
         *         ArrayList<SimpleGrantedAuthority> newList = new ArrayList<>();
         *         for (String permission : permissions) {
         *             newList.add(new SimpleGrantedAuthority(permission));
         *         }
         * //         赋值给成员变量
         *         authorities = newList;
         *         return newList;
         */


        //       方法二 函数式编程
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }




    // 获取密码 密码是加密的 如果是明文 需要在数据库密码前面加{noop}
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
