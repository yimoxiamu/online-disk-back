package com.xiamu.onlinediskback.controller;

import com.xiamu.onlinediskback.service.AdminService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录控制器
 */
@Log
@Controller
public class LoginControl {
    @Autowired
    AdminService adminService;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "index",method = RequestMethod.POST)
    public String dologin(@RequestParam("admin_name") String admin_name,
                          @RequestParam("admin_pass") String admin_pass){
        log.info("收到前端传递过来的参数，name="+admin_name+",pass="+admin_pass);
        String code=adminService.login(admin_name,admin_pass);
        if (code.equals("0000")){
            return "index";
        }else
            return "login";
    }
}
