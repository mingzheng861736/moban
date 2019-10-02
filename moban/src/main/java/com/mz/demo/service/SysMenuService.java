package com.mz.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mz.demo.entity.pojo.SysMenu;
import com.mz.demo.entity.vo.SysMenuVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weishilei
 * @since 2019-09-18
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 根据用户id获取菜单
     * @param userId 用户id
     * @return 菜单集合
     */
    List<SysMenuVo> getShowMenuByUser(Integer userId);

    /**
     * 根据参数获取所有
     * @param map 参数
     * @return 菜单集合
     */
    List<SysMenu> selectAllMenus(Map<String, Object> map);

    /**
     * 根据参数获取所有
     * @param map 参数
     * @return 菜单集合
     */
    List<SysMenu> selectAllMenuList(Map<String, Object> map);

    /**
     * 根据id查询
     * @param parentId id
     * @return 菜单
     */
    SysMenu selectById(Integer parentId);

    /**
     * 根据名称查询数量
     * @param name 名称
     * @return 数量
     */
    Integer getCountByName(String name);

    /**
     * 根据权限查询数量
     * @param permission 权限
     * @return 数量
     */
    Integer getCountByPermission(String permission);

    /**
     * 查询数量
     * @return 数量
     */
    Integer selectFirstLevelMenuMaxSort();

    /**
     * 根据父id查询数量
     * @param parentId 父id
     * @return 数量
     */
    Integer seleclMenuMaxSortByParentId(Integer parentId);

    /**
     * 保存或更新
     * @param menu 菜单
     */
    void saveOrUpdateMenu(SysMenu menu);
}