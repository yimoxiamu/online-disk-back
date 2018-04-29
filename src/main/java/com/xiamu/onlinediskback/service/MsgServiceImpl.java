package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.dao.MsgDao;
import com.xiamu.onlinediskback.entity.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    MsgDao msgDao;
    @Override
    public String pullMsg(String msg) {
        int rst= msgDao.pullMsg(msg);
        if (rst==1){
            return "0000";
        }
        return "1111";
    }

    @Override
    public List<MsgEntity> historyMsg() {
        List<MsgEntity> msgEntities= msgDao.historyMsg();
        return msgEntities;
    }
}
