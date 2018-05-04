package com.xiamu.onlinediskback.controller;

import com.xiamu.onlinediskback.service.UserFileService;
import com.xiamu.onlinediskback.util.CommonUtil;
import com.xiamu.onlinediskback.util.PageUtil;
import com.xiamu.onlinediskback.util.ResultUtil;
import com.xiamu.onlinediskback.util.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class FileControl {

    @Autowired
    UserFileService userFileService;

    @RequestMapping("file")
    public String File(){
        return "file";
    }

    //根据前端数据查找用户文件
    @RequestMapping("getUserFile")
    public String getUserFile(String UserName, String UserId, Model model){
        if(UserName.equals("")&&UserName==null&&UserId.equals("")&&UserId==null){
            return "file";
        }
        Map map= CommonUtil.mapFile;
        map.put("UserName",UserName);
        map.put("UserId",UserId);
        map.put("pageNum","1");
        map= SqlUtil.addZifu(map);
        Map<String,List> map1=userFileService.getUserFile(map);
        List list= PageUtil.pageNum(map1,"UserFileSize",8);
        model.addAttribute("UserFileList",map1.get("UserFile"));
        model.addAttribute("UserFileListSize",list);
        return "file";
    }


    @RequestMapping("getUserFile2")
    public String getUserFile(String pageNum,Model model){
        Map map=CommonUtil.mapFile;
        map.put("pageNum",pageNum);
        Map<String,List> map1=userFileService.getUserFile(map);
        List list= PageUtil.pageNum(map1,"UserFileSize",8);
        model.addAttribute("UserFileList",map1.get("UserFile"));
        model.addAttribute("UserFileListSize",list);
        return "file";
    }


    @RequestMapping("deleteFile")
    @ResponseBody
    public ResultUtil getUserFile(String fileId){
        ResultUtil resultUtil=userFileService.deleteFile(fileId);
        return resultUtil;
    }
}
