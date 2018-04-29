package com.xiamu.onlinediskback.dao;

import com.xiamu.onlinediskback.entity.MsgEntity;

import java.util.List;

public interface MsgDao {
    int pullMsg(String msg);
    List<MsgEntity> historyMsg();
}
