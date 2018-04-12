package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.dao.AdminDao;
import com.xiamu.onlinediskback.entity.AdminEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public String login(String admin_name, String admin_pass) {
//        AdminDao adminDao=new AdminDaoImpl();
        AdminEntity adminEntity = adminDao.login(admin_name);
        if (adminEntity != null) {
            if (adminEntity.getAdmin_pass().equals(admin_pass)) {
                log.info("用户名密码正确");
                return "0000";
            } else
                log.info("用户名存在，密码错误");
                return "1111";
        }
        log.info("用户不存在");
        return "1111";
    }
}
