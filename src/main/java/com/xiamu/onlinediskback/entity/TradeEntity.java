package com.xiamu.onlinediskback.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TradeEntity {
    private int id;
    private String tradeNo;
    private int userId;
    private double price;
    private String info;
    private Date time;
    private int success;
    private UserEntity userEntity;
    public TradeEntity() {
    }
}
