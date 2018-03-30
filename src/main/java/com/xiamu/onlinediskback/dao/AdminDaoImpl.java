package com.xiamu.onlinediskback.dao;

import com.xiamu.onlinediskback.entity.AdminEntity;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Log
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public AdminEntity login(String name) {
        String sql="select * from admin where admin_name=?";
        List<AdminEntity> adminEntities=jdbcTemplate.query(sql,new Object[]{name},new BeanPropertyRowMapper(AdminEntity.class));
        if(adminEntities.size()>0&&adminEntities!=null){
            return adminEntities.get(0);
        }else {
            return null;
        }
    }
}
