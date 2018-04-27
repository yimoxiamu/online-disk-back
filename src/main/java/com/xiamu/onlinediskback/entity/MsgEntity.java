package com.xiamu.onlinediskback.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MsgEntity {
 private int id;
 private String Msg;
 private Date pulltime;

    public MsgEntity() {
    }
}
