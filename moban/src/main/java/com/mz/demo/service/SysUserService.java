package com.mz.demo.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mz.demo.entity.pojo.SysRole;
import com.mz.demo.entity.pojo.SysUser;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weishilei
 * @since 2019-09-18
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名查询用户
     * @param name 用户名
     * @return 用户
     */
    SysUser findUserByLoginName(String name);

    /**
     * 根据用户id查询
     * @param id 用户id
     * @return 用户
     */
    SysUser findUserById(Integer id);

    /**
     * 根据用户名查询数量
     * @param loginName 用户名
     * @return 数量
     */
    Integer userCount(String loginName);

    /**
     * 保存用户角色
     * @param id 用户id
     * @param roleList 用户集合
     */
    void saveUserRoles(Integer id, Set<SysRole> roleList);

    /**
     * 保存用户
     * @param user 用户
     */
    void saveUser(SysUser user);

    /**
     * 修改用户
     * @param user 用户
     */
    void updateUser(SysUser user);

    /**
     * 删除用户
     * @param user 用户
     */
    void deleteUser(SysUser user);

    /**
     * 锁定用户
     * @param user 用户
     */
    void lockUser(SysUser user);
}
