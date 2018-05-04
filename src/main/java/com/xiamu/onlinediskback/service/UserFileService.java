package com.xiamu.onlinediskback.service;

import com.xiamu.onlinediskback.util.ResultUtil;

import java.util.Map;

public interface UserFileService {
    Map getUserFile(Map<String,String> map);
    ResultUtil deleteFile(String fileId);
}
