package com.mz.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.demo.entity.pojo.SysMenu;
import com.mz.demo.entity.vo.SysMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weishilei
 * @since 2019-09-18
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户查询菜单
     * @param map 参数
     * @return 菜单集合
     */
    List<SysMenuVo> selectShowMenuByUser(Map<String, Object> map);

    /**
     * 获取菜单
     * @param map 参数
     * @return 菜单集合
     */
    List<SysMenu> getMenus(Map<String, Object> map);
}
