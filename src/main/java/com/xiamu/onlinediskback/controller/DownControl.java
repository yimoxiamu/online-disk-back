package com.xiamu.onlinediskback.controller;

import com.xiamu.onlinediskback.service.DownService;
import com.xiamu.onlinediskback.util.CommonUtil;
import com.xiamu.onlinediskback.util.PageUtil;
import com.xiamu.onlinediskback.util.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class DownControl {
    @Autowired
    DownService downService;

    //根据前端传递过来的数据查找历史记录
    @RequestMapping("getAllDown")
    public String getAllDown(String uName, String fileName, String fileId, Model model) {
        Map map = CommonUtil.mapDown;
        map.put("pageNum","1");
        map.put("userName", uName);
        map.put("fileName", fileName);
        map.put("fileId", fileId);
        map = SqlUtil.addZifu(map);
        Map map1 = downService.getAllDown(map);
        List list= PageUtil.pageNum(map1,"downListSize",10);
        model.addAttribute("downList",map1.get("downList"));
        model.addAttribute("downListSize",list);
        return "down";
    }


    //根据缓存查找历史记录
    @RequestMapping("getAllDown2")
    public String getAllDown2(String pageNum,Model model){
        Map map=CommonUtil.mapDown;
        map.put("pageNum",pageNum);
        Map<String,List> map1=downService.getAllDown(map);
        List list= PageUtil.pageNum(map1,"downListSize",10);
        model.addAttribute("downList",map1.get("downList"));
        model.addAttribute("downListSize",list);
        return "down";
    }
}
