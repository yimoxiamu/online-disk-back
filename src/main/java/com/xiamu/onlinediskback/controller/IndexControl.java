package com.xiamu.onlinediskback.controller;

import com.alibaba.fastjson.JSON;
import com.xiamu.onlinediskback.entity.MsgEntity;
import com.xiamu.onlinediskback.entity.PageEntity;
import com.xiamu.onlinediskback.entity.UserEntity;
import com.xiamu.onlinediskback.service.MsgService;
import com.xiamu.onlinediskback.service.UserService;
import com.xiamu.onlinediskback.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class IndexControl {
    @Autowired
    UserService userService;

    @Autowired
    MsgService msgService;

    @RequestMapping("pay")
    public String pay(HttpServletRequest request,Model model){
        CookieUtil cookieUtil = new CookieUtil();
        if (cookieUtil.getCookieByName(request, "name") != null) {
            model.addAttribute("name", cookieUtil.getCookieByName(request, "name").getValue());
            return "pay";
        }
        return "login";
    }

    @RequestMapping("post")
    public String post(HttpServletRequest request,Model model){
        CookieUtil cookieUtil = new CookieUtil();
        if (cookieUtil.getCookieByName(request, "name") != null) {
            model.addAttribute("name", cookieUtil.getCookieByName(request, "name").getValue());
            return "post";
        }
        return "login";
    }


    @RequestMapping("user")
    public String toUser(HttpServletRequest request, Model model) {
        CookieUtil cookieUtil = new CookieUtil();
        if (cookieUtil.getCookieByName(request, "name") != null) {
            model.addAttribute("name", cookieUtil.getCookieByName(request, "name").getValue());
            return "user";
        }
        return "login";
    }

    //通过查找按钮获取用户列表
    @RequestMapping("getUsers")
    public String getUsers(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           String staus,
                           String vip
            , Model model) {
        Map map = CommonUtil.map;
        map.put("name", name);
        map.put("email", email);
        map.put("staus", staus);
        map.put("vip", vip);
        map.put("pageNum", "1");

        String jsonText = JSON.toJSONString(map);
        log.info("前端传递过来的参数为：" + jsonText);
        Map map1 = userService.getUsers(map);
        log.info("获取user总数成功，总数为：" + map1.get("listsize"));
        List list= PageUtil.pageNum(map1,"listsize");
        model.addAttribute("countList", list);
        model.addAttribute("userLists", map1.get("userlist"));
        return "user";
    }

    //通过缓存实现分页
    @RequestMapping("getUsers2")
    public String getUsers2(String pageNum, Model model) {
        Map map = CommonUtil.map;
        if (pageNum != null && !pageNum.equals("")) {
            map.put("pageNum", pageNum);
        } else {
            map.put("pageNum", (Integer) map.get("pageNum") + 1);
        }
        Map map1 = userService.getUsers(map);
        List list= PageUtil.pageNum(map1,"listsize");
        model.addAttribute("countList", list);
        model.addAttribute("userLists", map1.get("userlist"));
        return "user";
    }

    //通过name查找单个用户
    @ResponseBody
    @RequestMapping(value = "getUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String getUser(String name) {
        UserEntity userEntity = userService.getUserByName(name);
        String UserJson = JSON.toJSONString(userEntity);
        return UserJson;
    }

    //添加用户
    @RequestMapping(value = "insertUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String insertUser(String name, String pass, String email, String size,
                             String use, String vip_insert, String staus_insert, String msg) {
        Map map = new HashMap();
        map.put("name", name);
        map.put("pass", pass);
        map.put("email", email);
        map.put("size", size);
        map.put("use", use);
        map.put("vip", vip_insert);
        map.put("staus", staus_insert);
        map.put("msg", msg);
        Map map1 = SqlUtil.addZifu(map);
        userService.insertUser(map1);
        return "user";
    }


    //更新用户信息
    @RequestMapping(value = "updateUser", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateUser(String name, String pass, String email, String size,
                             String use, String vip_update, String staus_update, String id, String msg) {
        Map map = new HashMap();
        map.put("name", name);
        map.put("pass", pass);
        map.put("email", email);
        map.put("size", size);
        map.put("use", use);
        map.put("vip", vip_update);
        map.put("staus", staus_update);
        map.put("id", id);
        map.put("msg", msg);
        Map map1 = SqlUtil.addZifu(map);
        userService.updateUser(map1);
        return "user";
    }

    //发布用户通知
    @RequestMapping("pullMsg")
    public String pullMsg(String msg) {
        log.info("发布用户信息开始,前端传递过来的数据为：" + msg);
        String code = userService.updateMsg(msg);
        if (code.equals("0000")) {
            return "user";
        } else
            return "error";
    }


    //发布全服通告
    @ResponseBody
    @RequestMapping("allMsg")
    public ResultUtil allMsg(String msg) {
        ResultUtil resultUtil = new ResultUtil();
        log.info("发布全服通告开始,前端传递过来的数据为：" + msg);
        String code = msgService.pullMsg(msg);
        if (code.equals("0000")) {
            resultUtil.setRetCode("0000");
            resultUtil.setRetMsg("发布成功");
            return resultUtil;
        } else
            resultUtil.setRetCode("1111");
            resultUtil.setRetMsg("发布失败");
        return resultUtil;
    }

    @RequestMapping("historyMsg")
    public String historyMsg(Model model){
        List<MsgEntity> msgEntities= msgService.historyMsg();
        model.addAttribute("msgList",msgEntities);
        return "post";
    }

}
