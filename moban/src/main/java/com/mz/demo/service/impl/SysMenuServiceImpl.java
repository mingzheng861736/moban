package com.mz.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mz.demo.entity.pojo.SysMenu;
import com.mz.demo.entity.vo.SysMenuVo;
import com.mz.demo.mapper.SysMenuMapper;
import com.mz.demo.service.SysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weishilei
 * @since 2019-09-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenuVo> getShowMenuByUser(Integer userId) {
        Map<String, Object> map = new HashMap();
        map.put("userId", userId);
        map.put("parentId", null);

        return baseMapper.selectShowMenuByUser(map);
    }

    @Override
    public List<SysMenu> selectAllMenus(Map<String, Object> map) {
        return baseMapper.getMenus(map);
    }

    @Override
    public List<SysMenu> selectAllMenuList(Map<String, Object> map) {
        return baseMapper.selectByMap(map);
    }

    @Override
    public SysMenu selectById(Integer parentId) {
        return baseMapper.selectById(parentId);
    }

    @Override
    public Integer getCountByName(String name) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", false);
        wrapper.eq("name", name);

        return baseMapper.selectCount(wrapper);
    }

    @Override
    public Integer getCountByPermission(String permission) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", false);
        wrapper.eq("permission", permission);

        return baseMapper.selectCount(wrapper);
    }

    @Override
    public Integer selectFirstLevelMenuMaxSort() {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        Object o = getObj(wrapper.select("max(sort) as sort").isNull("parent_id"));

        return o == null ? 1 : ((SysMenu) o).getSort() + 1;
    }

    @Override
    public Integer seleclMenuMaxSortByParentId(Integer parentId) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        Object o = getObj(wrapper.select("max(sort) as sort").eq("parent_id", parentId));

        return o == null ? 1 : ((SysMenu) o).getSort() + 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateMenu(SysMenu menu) {
        saveOrUpdate(menu);
    }
}