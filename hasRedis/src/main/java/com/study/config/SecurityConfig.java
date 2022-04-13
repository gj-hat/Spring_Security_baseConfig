package com.study.config;

import com.study.filter.JwtAuthenticationTokenFilter;
import com.study.handler.AccessDeniedHandlerImpl;
import com.study.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/8 14:17
 * @description：密码验证 配置类
 * @modified By：
 * @version: 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;




    @Bean
    /**
     * SpringSecurity默认自动使用BCryptPasswordEncoder对密码进行加密，
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // configure()配置放行
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 登录页面放行 不拦截
        http
                // 关闭csrf防护     csrf是一个攻击  可以去百度了解
                .csrf().disable()
                // 不通过Sesdsion获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问   只有匿名时候才可以访问 /user/login
                .antMatchers("/user/login").anonymous()
                // 基于配置类的权限控制
                .antMatchers("/hello3").hasAuthority("system:dept:list")
                // 除上面外的所有请求去不需要鉴权认证
                .anyRequest().authenticated();


        // 添加JWT过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        // 配置异常处理器
        http.exceptionHandling()
                // 认证失败处理
                .authenticationEntryPoint(authenticationEntryPoint)
                // 授权失败处理
                .accessDeniedHandler(accessDeniedHandler);


        // 允许跨域
        http.cors();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
