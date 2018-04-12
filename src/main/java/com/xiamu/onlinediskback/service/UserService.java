package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserEntity> getUsers(Map map);
}
