package com.xiamu.onlinediskback.service;

import com.github.pagehelper.PageHelper;
import com.xiamu.onlinediskback.dao.UserFileDao;
import com.xiamu.onlinediskback.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    UserFileDao userFileDao;

    @Override
    public Map getUserFile(Map<String,String> map) {
        Map map1=new HashMap();
        int pageNum=Integer.valueOf(map.get("pageNum"));
        List UserFileSize=userFileDao.getUserFile(map);
        PageHelper.startPage(pageNum,8);
        List UserFile=userFileDao.getUserFile(map);
        map1.put("UserFile",UserFile);
        map1.put("UserFileSize",UserFileSize.size());
        return map1;
    }

    @Override
    public ResultUtil deleteFile(String fileId) {
        ResultUtil resultUtil=new ResultUtil();
        try {
            userFileDao.deleteFile(fileId);
        }catch (Exception e){
        resultUtil.setRetCode("1111");
        resultUtil.setRetMsg(e.getMessage());
        return resultUtil;
        }
        resultUtil.setRetCode("0000");
        resultUtil.setRetMsg("ok");
        return resultUtil;
    }
}
