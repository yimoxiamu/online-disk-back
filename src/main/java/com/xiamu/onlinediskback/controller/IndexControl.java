package com.xiamu.onlinediskback.controller;

import com.alibaba.fastjson.JSON;
import com.xiamu.onlinediskback.entity.PageEntity;
import com.xiamu.onlinediskback.entity.UserEntity;
import com.xiamu.onlinediskback.service.UserService;
import com.xiamu.onlinediskback.util.CommonUtil;
import com.xiamu.onlinediskback.util.CookieUtil;
import com.xiamu.onlinediskback.util.SqlUtil;
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


    @RequestMapping("user")
    public String toUser(HttpServletRequest request,Model model) {
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
        map.put("pageNum","1");

        String jsonText=JSON.toJSONString(map);
        log.info("前端传递过来的参数为："+jsonText);
        Map map1 = userService.getUsers(map);
        log.info("获取user总数成功，总数为："+map1.get("listsize"));
        int count=(Integer) map1.get("listsize");
        int pageCount;
        if (count%10==0){
            pageCount=count/10;
        }else {
            pageCount=count/10+1;
        }
        List list=new ArrayList();
        for (int i = 1; i <=pageCount; i++) {
            PageEntity pageEntity=new PageEntity();
            pageEntity.setCurrentPage(i);
            list.add(pageEntity);
        }
        model.addAttribute("countList",list);
        model.addAttribute("userLists", map1.get("userlist"));
        return "user";
    }

    //通过缓存实现分页
    @RequestMapping("getUsers2")
    public String getUsers2(String pageNum,Model model){
        Map map =CommonUtil.map;
        if(pageNum!=null&&!pageNum.equals("")){
            map.put("pageNum",pageNum);
        }else {
            map.put("pageNum",(Integer)map.get("pageNum")+1);
        }
        Map map1 = userService.getUsers(map);
        int count=(Integer) map1.get("listsize");
        int pageCount;
        if (count%10==0){
            pageCount=count/10;
        }else {
            pageCount=count/10+1;
        }
        List list=new ArrayList();
        for (int i = 1; i <=pageCount; i++) {
            PageEntity pageEntity=new PageEntity();
            pageEntity.setCurrentPage(i);
            list.add(pageEntity);
        }
        model.addAttribute("countList",list);
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
                             String use, String vip_insert, String staus_insert,String msg) {
        Map map = new HashMap();
        map.put("name", name);
        map.put("pass", pass);
        map.put("email", email);
        map.put("size", size);
        map.put("use", use);
        map.put("vip", vip_insert);
        map.put("staus", staus_insert);
        map.put("msg",msg);
        Map map1=SqlUtil.addZifu(map);
        userService.insertUser(map1);
        return "user";
    }


    //更新用户信息
    @RequestMapping(value = "updateUser",method = {RequestMethod.GET, RequestMethod.POST})
    public String updateUser(String name, String pass, String email, String size,
                             String use, String vip_update, String staus_update,String id,String msg){
        Map map=new HashMap();
        map.put("name", name);
        map.put("pass", pass);
        map.put("email", email);
        map.put("size", size);
        map.put("use", use);
        map.put("vip", vip_update);
        map.put("staus", staus_update);
        map.put("id",id);
        map.put("msg",msg);
        Map map1=SqlUtil.addZifu(map);
        userService.updateUser(map1);
        return "user";
    }

    //发布全服公告
    @RequestMapping("pullMsg")
    public String pullMsg(String msg){
        log.info("发布全服公告开始,前端传递过来的数据为："+msg);
        String code=userService.updateMsg(msg);
        if (code.equals("0000")){
            return "user";
        }else
            return "error";
    }

}
