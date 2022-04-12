package com.study.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：JiaGuo
 * @emil ：1520047927@qq.com
 * @date ：Created in 2022/4/11 20:40
 * @description：权限mapper
 * @modified By：
 * @version: 1.0
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuMapper>  {

     List<String> selectPermsByUserId(Long userid);

}
