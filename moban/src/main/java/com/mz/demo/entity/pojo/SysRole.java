package com.mz.demo.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.mz.demo.base.DataEntity;


import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author weishilei
 * @since 2019-09-18
 */
@TableName("sys_role")
public class SysRole extends DataEntity<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;

    @TableField(exist = false)
    private Set<SysMenu> menuSet;

    @TableField(exist = false)
    private Set<SysUser> userSet;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SysMenu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<SysMenu> menuSet) {
        this.menuSet = menuSet;
    }

    public Set<SysUser> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<SysUser> userSet) {
        this.userSet = userSet;
    }

}
