package com.study.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.web.entity.UserDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDomain> {
}
