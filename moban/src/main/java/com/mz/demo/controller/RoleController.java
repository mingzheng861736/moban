package com.mz.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mz.demo.annotation.SysLog;
import com.mz.demo.entity.pojo.SysMenu;
import com.mz.demo.entity.pojo.SysRole;
import com.mz.demo.entity.pojo.SysUser;
import com.mz.demo.service.SysMenuService;
import com.mz.demo.service.SysRoleService;
import com.mz.demo.service.SysUserService;
import com.mz.demo.util.CommonResult;
import com.mz.demo.util.PageData;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 角色Controller
 * @date 2019.09.18
 * @author weishilei
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 展现角色页面
     * @return 映射页面
     */
    @GetMapping
    public String showRoleList() {
        return "role/list";
    }

    @RequiresPermissions("sys:role:list")
    @PostMapping("/list")
    @ResponseBody
    public PageData<SysRole> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  ServletRequest request) {
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, "s_");
        PageData<SysRole> rolePageData = new PageData<>();
        QueryWrapper<SysRole> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq("del_flag", false);
        if (!map.isEmpty()) {
            String keys = (String) map.get("key");
            if (StringUtils.isNotBlank(keys)) {
                roleWrapper.like("name", keys);
            }
        }

        IPage<SysRole> rolePage = sysRoleService.page(new Page<>(page, limit), roleWrapper);
        rolePageData.setCount(rolePage.getTotal());
        rolePageData.setData(setUserToRole(rolePage.getRecords()));

        return rolePageData;
    }

    private List<SysRole> setUserToRole(List<SysRole> roles) {
        roles.forEach(role -> {
            if (null != role.getCreateId()) {
                SysUser sysUser = sysUserService.findUserById(role.getCreateId());
                if (StringUtils.isBlank(sysUser.getNickName())) {
                    sysUser.setNickName(sysUser.getLoginName());
                }
                role.setCreateUser(sysUser);
            }
            if (null != role.getUpdateId()) {
                SysUser sysUser = sysUserService.findUserById(role.getUpdateId());
                if (StringUtils.isBlank(sysUser.getNickName())) {
                    sysUser.setNickName(sysUser.getLoginName());
                }
                role.setUpdateUser(sysUser);
            }
        });

        return roles;
    }


    /**
     * 展现新增页面
     * @param modelMap 参数
     * @return 映射页面
     */
    @GetMapping("/add")
    public String showAdd(ModelMap modelMap) {
        Map<String, Object> map = new HashMap();
        map.put("parentId", null);
        map.put("isShow", false);
        List<SysMenu> menuList = sysMenuService.selectAllMenus(map);
        modelMap.put("menuList", menuList);

        return "role/add";
    }

    /**
     * 新增角色
     * @param role 角色
     * @return 响应结果
     */
    @RequiresPermissions("sys:role:add")
    @PostMapping("/add")
    @ResponseBody
    @SysLog("保存新增角色数据")
    public CommonResult add(@RequestBody SysRole role) {
        if (StringUtils.isBlank(role.getName())) {
            return CommonResult.failed("角色名称不能为空");
        }
        if (sysRoleService.getRoleNameCount(role.getName()) > 0) {
            return CommonResult.failed("角色名称已存在");
        }

        sysRoleService.saveRole(role);
        return CommonResult.success("操作成功");
    }

    /**
     * 展现修改页面
     * @param modelMap 参数
     * @return 映射页面
     */
    @GetMapping("/edit")
    public String edit(Integer id, ModelMap modelMap) {
        SysRole role = sysRoleService.getRoleById(id);
        String menuIds = null;
        if (role != null) {
            menuIds = role.getMenuSet().stream().map(menu -> menu.getId() + "").collect(Collectors.joining(","));
        }
        Map<String, Object> map = new HashMap();
        map.put("parentId", null);
        map.put("isShow", Boolean.FALSE);
        List<SysMenu> menuList = sysMenuService.selectAllMenus(map);
        modelMap.put("role", role);
        modelMap.put("menuList", menuList);
        modelMap.put("menuIds", menuIds);

        return "role/edit";
    }

    /**
     * 修改角色
     * @param role 角色
     * @return 响应结果
     */
    @RequiresPermissions("sys:role:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑角色数据")
    public CommonResult edit(@RequestBody SysRole role) {
        if (null == role.getId()) {
            return CommonResult.failed("角色ID不能为空");
        }
        if (StringUtils.isBlank(role.getName())) {
            return CommonResult.failed("角色名称不能为空");
        }
        SysRole oldRole = sysRoleService.getRoleById(role.getId());
        if (!oldRole.getName().equals(role.getName())) {
            if (sysRoleService.getRoleNameCount(role.getName()) > 0) {
                return CommonResult.failed("角色名称已存在");
            }
        }
        sysRoleService.updateRole(role);

        return CommonResult.success("操作成功");
    }

    /**
     * 删除角色
     * @param id 唯一标识
     * @return 响应结果
     */
    @RequiresPermissions("sys:role:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除角色数据")
    public CommonResult delete(@RequestParam(value = "id", required = false) Integer id) {
        if (null != id) {
            return CommonResult.failed("角色ID不能为空");
        }
        SysRole role = sysRoleService.getRoleById(id);
        sysRoleService.deleteRole(role);

        return CommonResult.success("操作成功");
    }

    /**
     * 删除多个id
     * @param roles 角色集合
     * @return 响应结果
     */
    @RequiresPermissions("sys:role:delete")
    @PostMapping("deleteChecked")
    @ResponseBody
    @SysLog("多选删除角色数据")
    public CommonResult deleteSome(@RequestBody List<SysRole> roles) {
        if (roles == null || roles.size() == 0) {
            return CommonResult.failed("请选择需要删除的角色");
        }

        for (SysRole role : roles) {
            sysRoleService.deleteRole(role);
        }

        return CommonResult.success("操作成功");
    }
}
