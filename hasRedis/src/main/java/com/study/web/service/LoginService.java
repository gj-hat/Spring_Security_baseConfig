package com.study.web.service;

import com.study.web.entity.UserDomain;
import com.study.web.entity.dto.ResponseResult;

public interface LoginService {
    ResponseResult login(UserDomain user);

    ResponseResult logout();
}
