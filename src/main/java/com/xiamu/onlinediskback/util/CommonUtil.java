package com.xiamu.onlinediskback.util;

import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    public static Map<String,String> map=new HashMap<>();        //缓存查询用户数据
    public static Map<String,String> mapPay=new HashMap<>();        //缓存查询订单数据
    public static Map<String,String> mapDown=new HashMap<>();        //缓存下载记录数据
    public static Map<String,String> mapFile=new HashMap<>();        //缓存用户文件数据
}
