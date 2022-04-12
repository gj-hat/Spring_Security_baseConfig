package com.study.web.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (SysRoleMenu)表实体类
 *
 * @author makejava
 * @since 2022-04-11 17:07:43
 */
@SuppressWarnings("serial")
public class SysRoleMenu extends Model<SysRoleMenu> {
    //角色ID
    private Long roleId;
    //菜单id
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
    }

