package com.xiamu.onlinediskback.dao;

import com.xiamu.onlinediskback.entity.UserFileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserFileDao {
    List<UserFileEntity> getUserFile(Map map);
    void deleteFile(String fileId);
}
