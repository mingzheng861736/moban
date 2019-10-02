package com.mz.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mz.demo.entity.pojo.SysRole;
import com.mz.demo.mapper.SysRoleMapper;
import com.mz.demo.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weishilei
 * @since 2019-09-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public long getRoleNameCount(String name) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);

        return baseMapper.selectCount(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysRole saveRole(SysRole role) {
        baseMapper.insert(role);
        if (role.getMenuSet() != null && role.getMenuSet().size() > 0) {
            baseMapper.saveRoleMenus(role.getId(), role.getMenuSet());
        }
        return role;
    }

    @Override
    public SysRole getRoleById(Integer id) {
        return baseMapper.selectRoleById(id);
    }

    @Override
    public void updateRole(SysRole role) {
        baseMapper.updateById(role);
        baseMapper.dropRoleMenus(role.getId());
        if (role.getMenuSet() != null && role.getMenuSet().size() > 0) {
            baseMapper.saveRoleMenus(role.getId(), role.getMenuSet());
        }
    }

    @Override
    public void deleteRole(SysRole role) {
        role.setDelFlag(1);

        baseMapper.updateById(role);
        baseMapper.dropRoleMenus(role.getId());
        baseMapper.dropRoleUsers(role.getId());
    }

    @Override
    public List<SysRole> selectAll() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("del_flag", 0);

        return baseMapper.selectList(wrapper);

    }
}
