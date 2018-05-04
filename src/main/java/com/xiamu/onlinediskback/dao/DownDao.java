package com.xiamu.onlinediskback.dao;

import com.xiamu.onlinediskback.entity.DownEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DownDao {
    List<DownEntity> getAllDown(Map map);
}
