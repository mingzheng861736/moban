package com.mz.demo.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.demo.entity.pojo.SysRole;
import com.mz.demo.entity.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
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
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户信息查询
     * @param map 数据
     * @return 用户
     */
    SysUser selectUserByMap(Map<String, Object> map);

    /**
     * 保存用户角色
     * @param id 用户id
     * @param roles 角色集合
     * @return 影响行数
     */
    Integer saveUserRoles(@Param("userId") Integer id, @Param("roleIds") Set<SysRole> roles);

    /**
     * 删除用户角色
     * @param id 用户id
     * @return 影响行数
     */
    Integer dropUserRolesByUserId(@Param("userId") Integer id);
}
