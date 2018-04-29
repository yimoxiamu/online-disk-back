package com.xiamu.onlinediskback.dao;

import com.xiamu.onlinediskback.entity.MsgEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class MsgDaoImpl implements MsgDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int pullMsg(String msg) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dat=sdf.format(date);
        String sql="insert into servermsg (Msg,pullTime) values(?,?)";
        int rst=jdbcTemplate.update(sql,new Object[]{msg,dat});
        return rst;
    }

    @Override
    public List<MsgEntity> historyMsg() {
        List<MsgEntity> msgEntities=new ArrayList<>();
        String sql="select * from servermsg ORDER BY  pulltime DESC ";
        msgEntities=jdbcTemplate.query(sql,new BeanPropertyRowMapper(MsgEntity.class));
        return msgEntities;
    }
}
