package com.mz.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mz.demo.entity.pojo.SysRole;
import com.mz.demo.entity.pojo.SysUser;
import com.mz.demo.mapper.SysUserMapper;
import com.mz.demo.service.SysUserService;
import com.mz.demo.util.Encodes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findUserByLoginName(String name) {
        Map<String, Object> map = new HashMap();
        map.put("loginName", name);

        return baseMapper.selectUserByMap(map);
    }

    @Override
    public SysUser findUserById(Integer id) {
        Map<String, Object> map = new HashMap();
        map.put("id", id);

        return baseMapper.selectUserByMap(map);
    }

    @Override
    public Integer userCount(String loginName) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", loginName).or().eq("email", loginName).or().eq("tel", loginName);
        int count = baseMapper.selectCount(wrapper);

        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRoles(Integer id, Set<SysRole> roleList) {
        baseMapper.saveUserRoles(id, roleList);
    }

    @Override
    public void saveUser(SysUser user) {
        Encodes.entryptPassword(user);
        user.setLocked(0);

        baseMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(SysUser user) {
        dropUserRolesByUserId(user.getId());

        baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(SysUser user) {
        user.setDelFlag(1);
        user.updateById();
    }

    @Override
    public void lockUser(SysUser user) {
        Integer locked = user.getLocked() == 1 ? 0 : 1;
        user.setLocked(locked);

        user.updateById();
    }

    @Transactional(rollbackFor = Exception.class)
    public void dropUserRolesByUserId(Integer id) {
        baseMapper.dropUserRolesByUserId(id);
    }
}
