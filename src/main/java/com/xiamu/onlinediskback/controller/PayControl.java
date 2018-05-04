package com.xiamu.onlinediskback.controller;

import com.xiamu.onlinediskback.service.TradeService;
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
public class PayControl {
    @Autowired
    TradeService tradeService;

    //通过前端传参查找订单
    @RequestMapping("getAllPay")
    public String getAllPay(String orderId, String uId, Model model){
        log.info("查询订单开始，前端传递过来的数据为：orderId="+orderId+"uId="+uId);
        Map map= CommonUtil.mapPay;
        map.put("tradeNo",orderId);
        map.put("userId",uId);
        map.put("pageNum","1");
        map= SqlUtil.addZifu(map);
        Map map1=tradeService.getAllPay(map);
        log.info("获得总数目为："+map1.get("tradeEntitiesize"));
        List list= PageUtil.pageNum(map1,"tradeEntitiesize",10);
        model.addAttribute("tradeList",map1.get("tradeEntities"));
        model.addAttribute("tradeListSize",list);
        return "pay";
    }

    //通过缓存查找订单
    @RequestMapping("getAllPay2")
    public String getAllPay2(String pageNum,Model model){
        Map map=CommonUtil.mapPay;
        map.put("pageNum",pageNum);
        Map map1=tradeService.getAllPay(map);
        List list= PageUtil.pageNum(map1,"tradeEntitiesize",10);
        model.addAttribute("tradeList",map1.get("tradeEntities"));
        model.addAttribute("tradeListSize",list);
        return "pay";
    }
}
