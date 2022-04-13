package com.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/11 22:05
 * @description：Cores for Spring Boot
 * @modified By：
 * @version: 1.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // 配置SpringBoot的跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置运行跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*/**")
                // 设置是否允许Cookie
                .allowCredentials(true)
                // 设置允许跨域请求的方法
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许跨域请求的时间
                .maxAge(3600)
                // 设置允许跨域请求的头信息
                .allowedHeaders("*");
    }


}
