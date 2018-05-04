package com.xiamu.onlinediskback.service;

import com.github.pagehelper.PageHelper;
import com.xiamu.onlinediskback.dao.DownDao;
import com.xiamu.onlinediskback.entity.DownEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DownServiceImpl implements DownService {

    @Autowired
    DownDao downDao;

    public Map getAllDown(Map<String,String> map) {
        int pageNum=Integer.valueOf(map.get("pageNum"));
        List downListSize=downDao.getAllDown(map);
        PageHelper.startPage(pageNum,10);
        List downList=downDao.getAllDown(map);
        Map map1=new HashMap<>();
        map1.put("downListSize",downListSize.size());
        map1.put("downList",downList);
        return map1;
    }
}
