package com.mz.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mz.demo.entity.pojo.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weishilei
 * @since 2019-09-18
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 根据角色名查询
     * @param name 角色名
     * @return 总数
     */
    long getRoleNameCount(String name);

    /**
     * 保存角色
     * @param role 角色
     * @return
     */
    SysRole saveRole(SysRole role);

    /**
     * 根据id查询角色
     * @param id 角色id
     * @return
     */
    SysRole getRoleById(Integer id);

    /**
     * 修改角色
     * @param role 角色
     */
    void updateRole(SysRole role);

    /**
     * 删除角色
     * @param role 角色
     */
    void deleteRole(SysRole role);

    /**
     * 查询全部角色
     * @return 角色集合
     */
    List<SysRole> selectAll();
}
