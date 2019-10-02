package com.mz.demo.controller;
import com.mz.demo.entity.vo.SysMenuVo;
import com.mz.demo.realm.AuthRealm;
import com.mz.demo.service.SysMenuService;
import com.mz.demo.util.Constants;
import com.mz.demo.util.SysUserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 首页Controller
 * @date 2019.09.18
 * @author weishilei
 */
@Controller
@RequestMapping("/admin/index")
public class IndexController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 展现首页
     * @return 映射页面
     */
    @GetMapping
    public String showIndex(HttpSession session, @ModelAttribute(Constants.LOGIN_TYPE) String loginType) {
        if (StringUtils.isBlank(loginType)) {
            Constants.LoginTypeEnum attribute = (Constants.LoginTypeEnum) session.getAttribute(Constants.LOGIN_TYPE);
            loginType = attribute == null ? loginType : attribute.name();
        }
        if (Constants.LoginTypeEnum.ADMIN.name().equals(loginType)) {
            AuthRealm.ShiroUser principal = (AuthRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("icon", StringUtils.isBlank(principal.getIcon()) ? "/static/admin/img/face.jpg" : principal.getIcon());
        }

        return "index";
    }

    /***
     * 获得用户所拥有的菜单列表
     * @return
     */
    @GetMapping("/getUserMenu")
    @ResponseBody
    public List<SysMenuVo> getUserMenu() {
        Integer userId = SysUserUtils.id();
        List<SysMenuVo> list = sysMenuService.getShowMenuByUser(userId);

        return list;
    }


}
