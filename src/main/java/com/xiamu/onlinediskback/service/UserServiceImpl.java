package com.xiamu.onlinediskback.service;

import com.github.pagehelper.PageHelper;
import com.xiamu.onlinediskback.dao.UserDao;
import com.xiamu.onlinediskback.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public Map<String,Object> getUsers(Map<String,String> map) {
        int pageNum=Integer.valueOf(map.get("pageNum"));
        List<UserEntity> userEntities1= userDao.getUserList(map);
        PageHelper.startPage(pageNum,10);
        List<UserEntity> userEntities= userDao.getUserList(map);
        Map<String,Object> map1=new HashMap();
        map1.put("userlist",userEntities);
        map1.put("listsize",userEntities1.size());
        return map1;
    }

    @Override
    public UserEntity getUserByName(String name) {
        UserEntity userEntity=userDao.getUserByName(name);
        return userEntity;
    }

    @Override
    public void insertUser(Map map) {
        userDao.insertUser(map);
    }

    @Override
    public void updateUser(Map map) {
        userDao.updateUser(map);
    }

    @Override
    public String updateMsg(String msg) {
        try{
            userDao.updateMsg(msg);
        }catch (Exception e){
            log.error("修改数据库失败，错误信息为："+e);
            return "1111";
        }
        log.info("修改数据库内信息成功");
        return "0000";
    }
}
