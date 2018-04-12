package com.xiamu.onlinediskback.controller;

import com.xiamu.onlinediskback.entity.UserEntity;
import com.xiamu.onlinediskback.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.applet.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class IndexControl {
    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public String toUser(){
        return "user";
    }


    @RequestMapping("getUsers")
    public String getUsers(@RequestParam("name") String name,
                           @RequestParam("email")String email,
                           String staus
                            , Model model){
        Map map=new HashMap();
        map.put("name",name);
        map.put("email",email);
        map.put("staus",staus);
        List<UserEntity> Userlist= userService.getUsers(map);
        model.addAttribute("userLists",Userlist);
        return "user";
    }
}
