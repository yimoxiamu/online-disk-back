package com.xiamu.onlinediskback.controller;

import com.xiamu.onlinediskback.entity.UserEntity;
import com.xiamu.onlinediskback.service.AdminService;
import com.xiamu.onlinediskback.service.UserService;
import com.xiamu.onlinediskback.util.CookieUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录控制器
 */
@Slf4j
@Controller
public class LoginControl {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    @RequestMapping("login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {
        CookieUtil cookieUtil = new CookieUtil();
        if (cookieUtil.getCookieByName(request, "name") != null) {
            model.addAttribute("name", cookieUtil.getCookieByName(request, "name").getValue());
            return "index";
        }
        return "login";

    }

    @RequestMapping(value = "index", method = {RequestMethod.POST, RequestMethod.GET})
    public String dologin(String admin_name,
                          String admin_pass,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          String check,
                          Model model) {

        CookieUtil cookieUtil = new CookieUtil();
        if (cookieUtil.getCookieByName(request, "name") != null) {
            model.addAttribute("name", cookieUtil.getCookieByName(request, "name").getValue());
            return "index";
        }

        if (admin_name != null && admin_pass != null) {
            log.info("收到前端传递过来的参数，name=" + admin_name + ",pass=" + admin_pass + ",check=" + check);
            String code = adminService.login(admin_name, admin_pass);
            if (code.equals("0000")) {
                if (check!=null&&!check.equals("")) {
                    Cookie cookie = new Cookie("name", admin_name);
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);
                }
                model.addAttribute("name", admin_name);
                return "index";
            } else
                return "login";
        }
        return "login";
    }


}

