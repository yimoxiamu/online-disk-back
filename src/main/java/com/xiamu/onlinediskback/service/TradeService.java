package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.entity.TradeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface TradeService {
    Map getAllPay(Map<String,String> map);
}
