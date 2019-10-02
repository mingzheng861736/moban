package com.mz.demo.controller;

import com.mz.demo.annotation.SysLog;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台相关Controller
 * @date 2019.09.18
 * @author weishilei
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * 展现主页
     * @return 映射页面
     */
    @GetMapping("/main")
    public String showMain() {
        return "main";
    }

    /**
     * 退出系统
     * @return 重定向页面
     */
    @GetMapping("/logout")
    @SysLog("退出系统")
    public String logOut() {
        SecurityUtils.getSubject().logout();
        return "redirect:/admin/login";
    }
}