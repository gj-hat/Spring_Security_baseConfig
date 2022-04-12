package com.study.handler;

import com.alibaba.fastjson.JSON;
import com.study.utils.WebUtils;
import com.study.web.entity.dto.ResponseResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/11 21:54
 * @description： 授权过程出现问题  异常实现类
 * @modified By：
 * @version: 1.0
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {


        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "您的权限不足，请联系管理员");
        String s = JSON.toJSONString(result);
        // 处理异常
        WebUtils.renderString(response, s);



    }
}
