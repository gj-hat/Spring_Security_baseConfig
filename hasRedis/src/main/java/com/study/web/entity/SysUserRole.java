package com.study.web.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (SysUserRole)表实体类
 *
 * @author makejava
 * @since 2022-04-11 17:07:46
 */
@SuppressWarnings("serial")
public class SysUserRole extends Model<SysUserRole> {
    //用户id
    private Long userId;
    //角色id
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.userId;
    }
    }

