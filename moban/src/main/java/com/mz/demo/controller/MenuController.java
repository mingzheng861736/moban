package com.mz.demo.controller;
import com.mz.demo.annotation.SysLog;
import com.mz.demo.entity.pojo.SysMenu;
import com.mz.demo.service.SysMenuService;
import com.mz.demo.util.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单Controller
 * @date 2019.09.19
 * @author weishilei
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 展现菜单列表
     * @return 映射页面
     */
    @GetMapping
    public String showMenuList() {
        return "menu/list";
    }

    /**
     * 获取树形集合
     * @return 响应结果
     */
    @RequiresPermissions("sys:menu:list")
    @RequestMapping("/treeList")
    @ResponseBody
    @GetMapping
    public CommonResult treeList() {
        Map<String, Object> param = new HashMap();
        param.put("del_flag", false);
        List<SysMenu> menus = sysMenuService.selectAllMenuList(param);
        menus.forEach(menu -> {
            if (null == menu.getParentId()) {
                menu.setParentId(-1);
            }
        });
        menus.sort(Comparator.comparing(SysMenu::getSort));

        return CommonResult.success(menus);
    }

    /**
     * 展现新增菜单
     * @param parentId 父id
     * @param modelMap 参数
     * @return 映射页面
     */
    @GetMapping("/add")
    public String showAdd(@RequestParam(value = "parentId", required = false) Integer parentId, ModelMap modelMap) {
        if (parentId != null) {
            SysMenu menu = sysMenuService.selectById(parentId);
            modelMap.put("parentMenu", menu);
        }

        return "menu/add";
    }

    /**
     * 新增菜单
     * @param menu 菜单对象
     * @return 响应结果
     */
    @RequiresPermissions("sys:menu:add")
    @PostMapping("/add")
    @ResponseBody
    @SysLog("保存新增菜单数据")
    public CommonResult add(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            return CommonResult.failed("菜单名称不能为空");
        }
        if (sysMenuService.getCountByName(menu.getName()) > 0) {
            return CommonResult.failed("菜单名称已存在");
        }
        if (StringUtils.isNotBlank(menu.getPermission())) {
            if (sysMenuService.getCountByPermission(menu.getPermission()) > 0) {
                return CommonResult.failed("权限标识已经存在");
            }
        }
        if (menu.getParentId() == null) {
            menu.setLevel(1);
            menu.setSort(sysMenuService.selectFirstLevelMenuMaxSort());
        } else {
            SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
            if (parentMenu == null) {
                return CommonResult.failed("父菜单不存在");
            }
            menu.setParentIds(parentMenu.getParentIds());
            menu.setLevel(parentMenu.getLevel() + 1);
            menu.setSort(sysMenuService.seleclMenuMaxSortByParentId(menu.getParentId()));
        }
        sysMenuService.saveOrUpdateMenu(menu);
        menu.setParentIds(StringUtils.isBlank(menu.getParentIds()) ? menu.getId() + "," : menu.getParentIds() + menu.getId() + ",");
        sysMenuService.saveOrUpdateMenu(menu);

        return CommonResult.success("操作成功");
    }

    /**
     * 展现修改页面
     * @param id 唯一标识
     * @param modelMap 参数
     * @return 映射页面
     */
    @GetMapping("/edit")
    public String showEdit(Integer id, ModelMap modelMap) {
        SysMenu menu = sysMenuService.selectById(id);
        modelMap.addAttribute("menu", menu);
        return "menu/edit";
    }

    /**
     * 修改菜单
     * @param menu 菜单
     * @return 响应结果
     */
    @RequiresPermissions("sys:menu:edit")
    @PostMapping("/edit")
    @ResponseBody
    @SysLog("保存编辑菜单数据")
    public CommonResult edit(SysMenu menu) {
        if (null == menu.getId()) {
            return CommonResult.failed("菜单ID不能为空");
        }
        if (StringUtils.isBlank(menu.getName())) {
            return CommonResult.failed("菜单名称不能为空");
        }

        SysMenu oldMenu = sysMenuService.selectById(menu.getId());
        if (!oldMenu.getName().equals(menu.getName())) {
            if (sysMenuService.getCountByName(menu.getName()) > 0) {
                return CommonResult.failed("菜单名称已存在");
            }
        }
        if (StringUtils.isNotBlank(menu.getPermission())) {
            if (!oldMenu.getPermission().equals(menu.getPermission())) {
                if (sysMenuService.getCountByPermission(menu.getPermission()) > 0) {
                    return CommonResult.failed("权限标识已经存在");
                }
            }
        }
        if (menu.getSort() == null) {
            return CommonResult.failed("排序值不能为空");
        }

        sysMenuService.saveOrUpdateMenu(menu);
        return CommonResult.success("操作成功");
    }

    /**
     * 删除菜单
     * @param id 唯一标识
     * @return 响应结果
     */
    @RequiresPermissions("sys:menu:delete")
    @PostMapping("/delete")
    @ResponseBody
    @SysLog("删除菜单")
    public CommonResult delete(@RequestParam(value = "id", required = false) Integer id) {
        if (null == id) {
            return CommonResult.failed("菜单ID不能为空");
        }
        SysMenu menu = sysMenuService.selectById(id);
        menu.setDelFlag(1);
        sysMenuService.saveOrUpdateMenu(menu);

        return CommonResult.success("操作成功");
    }

}
