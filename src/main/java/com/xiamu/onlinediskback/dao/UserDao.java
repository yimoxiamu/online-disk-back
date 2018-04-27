package com.xiamu.onlinediskback.dao;

import com.github.pagehelper.PageHelper;
import com.xiamu.onlinediskback.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    List<UserEntity> getUserList(Map map);

    UserEntity getUserByName(String name);

    void insertUser(Map map);

    void updateUser(Map map);

    void updateMsg(String msg);
}
