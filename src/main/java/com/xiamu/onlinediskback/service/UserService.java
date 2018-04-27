package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> getUsers(Map<String,String> map);
    UserEntity getUserByName(String name);
    void insertUser(Map map);
    void updateUser(Map map);
    String updateMsg(String msg);
}
