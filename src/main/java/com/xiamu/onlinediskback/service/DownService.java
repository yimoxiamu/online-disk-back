package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.entity.DownEntity;

import java.util.List;
import java.util.Map;

public interface DownService {
    Map<String,List> getAllDown(Map<String,String> map);
}
