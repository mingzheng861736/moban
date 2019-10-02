package com.mz.demo.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.demo.entity.pojo.SysMenu;
import com.mz.demo.entity.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weishilei
 * @since 2019-09-18
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 保存角色菜单
     * @param roleId 角色id
     * @param menuSet 菜单集合
     */
    void saveRoleMenus(@Param("roleId") Integer roleId, @Param("menus") Set<SysMenu> menuSet);

    /**
     * 根据角色id
     * @param roleId 角色id
     * @return 角色
     */
    SysRole selectRoleById(@Param("roleId") Integer roleId);

    /**
     * 根据角色id删除菜单
     * @param roleId 角色id
     */
    void dropRoleMenus(@Param("roleId") Integer roleId);

    /**
     * 根据角色id删除用户
     * @param roleId 角色id
     */
    void dropRoleUsers(@Param("roleId") Integer roleId);
}
