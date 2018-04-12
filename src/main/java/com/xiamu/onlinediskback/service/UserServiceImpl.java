package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.dao.UserDao;
import com.xiamu.onlinediskback.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<UserEntity> getUsers(Map map) {
        List<UserEntity> userEntities= userDao.getUserList(map);
        return userEntities;
    }
}
