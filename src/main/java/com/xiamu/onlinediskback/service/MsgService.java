package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.entity.MsgEntity;

import java.util.List;

public interface MsgService {
    String pullMsg(String msg);
    List<MsgEntity> historyMsg();
}
