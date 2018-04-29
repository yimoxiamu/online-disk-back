package com.xiamu.onlinediskback.dao;

import com.xiamu.onlinediskback.entity.TradeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TradeDao {
    List<TradeEntity> getAllPay(Map map);
}
