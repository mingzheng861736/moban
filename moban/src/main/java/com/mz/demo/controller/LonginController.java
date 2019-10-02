package com.mz.demo.controller;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.mz.demo.annotation.SysLog;
import com.mz.demo.exception.UserTypeAccountException;
import com.mz.demo.service.SysMenuService;
import com.mz.demo.util.CommonResult;
import com.mz.demo.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;


/**
 * 用户登录Controller
 * @date 2019.09.18
 * @author weishilei
 */
@Controller
@Validated
@RequestMapping("/admin/login")
public class LonginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LonginController.class);

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping
    public String adminIndex(RedirectAttributes attributes) {
        Subject subject = SecurityUtils.getSubject();
        attributes.addFlashAttribute(Constants.LOGIN_TYPE, Constants.LoginTypeEnum.ADMIN);
        if (subject.isAuthenticated()) {
            return "redirect:/admin/index";
        }

        return "redirect:/admin/login/toLogin";
    }


    /**
     * 展现登录页面
     * @return 映射页面
     */
    @GetMapping(value = "toLogin")
    public String showLogin(HttpSession session) {
        session.setAttribute(Constants.LOGIN_TYPE, Constants.LoginTypeEnum.ADMIN);
        return "login";
    }

    /**
     * 用户登录
     * @param request 请求
     * @param username 用户名
     * @param password 密码
     * @param rememberMe 是否记住
     * @param code 验证码
     * @return 响应结果
     */
    @PostMapping
    @SysLog("用户登录")
    @ResponseBody
    public CommonResult adminLogin(HttpServletRequest request, String rememberMe,
                                   @NotBlank(message = "用户名或密码错误") String username,
                                   @NotBlank(message = "用户名或密码错误")String password,
                                   @NotBlank(message = "验证码错误")String code) {
        HttpSession session = request.getSession();
        if (session == null) {
            return CommonResult.failed("session超时");
        }
        String trueCode = (String) session.getAttribute(Constants.VALIDATE_CODE);
        if (StringUtils.isBlank(trueCode)) {
            return CommonResult.failed("验证码超时");
        }
        if (StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())) {
            return CommonResult.failed("验证码错误");
        } else {
            /*当前用户*/
            String errorMsg = null;
            Subject user = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, Boolean.valueOf(rememberMe));
            try {
                user.login(token);
                LOGGER.debug(username + "用户" + LocalDate.now().toString() + ":======》登陆系统!");
            } catch (IncorrectCredentialsException e) {
                errorMsg = "用户名密码错误!";
            } catch (UnknownAccountException e) {
                errorMsg = "账户不存在";
            } catch (LockedAccountException e) {
                errorMsg = "账户已被锁定";
            } catch (UserTypeAccountException e) {
                errorMsg = "账户不是管理用户";
            }

            if (StringUtils.isBlank(errorMsg)) {
                return CommonResult.success("/admin/index");
            } else {
                return CommonResult.failed(errorMsg);
            }
        }
    }

    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = captchaProducer.createText();
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
        LOGGER.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = captchaProducer.createImage(verifyCode);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }
}
