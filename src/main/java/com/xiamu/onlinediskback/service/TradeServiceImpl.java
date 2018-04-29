package com.xiamu.onlinediskback.service;

import com.github.pagehelper.PageHelper;
import com.xiamu.onlinediskback.dao.TradeDao;
import com.xiamu.onlinediskback.entity.TradeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TradeServiceImpl implements TradeService {
    @Autowired
    TradeDao tradeDao;
    @Override
    public Map getAllPay(Map<String,String> map) {
        int pageNum=Integer.valueOf(map.get("pageNum"));
        List<TradeEntity> tradeEntities= tradeDao.getAllPay(map);
        PageHelper.startPage(pageNum,10);
        List<TradeEntity> tradeEntitiesize= tradeDao.getAllPay(map);
        Map map1=new HashMap();
        map1.put("tradeEntities",tradeEntitiesize);
        map1.put("tradeEntitiesize",tradeEntities.size());
        return map1;
    }
}
