package com.xiamu.onlinediskback.util;

import java.util.Map;

public class SqlUtil {
    public static Map addZifu(Map map){
        if (map.get("name")!=null){
            map.put("name","\'"+map.get("name")+"\'");
        }
        if (map.get("pass")!=null){
            map.put("pass","\'"+map.get("pass")+"\'");
        }
        if (map.get("email")!=null){
            map.put("email","\'"+map.get("email")+"\'");
        }
        if (map.get("size")!=null){
            map.put("size","\'"+map.get("size")+"\'");
        }
        if (map.get("use")!=null){
            map.put("use","\'"+map.get("use")+"\'");
        }
        if (map.get("vip")!=null){
            map.put("vip","\'"+map.get("vip")+"\'");
        }
        if (map.get("staus")!=null){
            map.put("staus","\'"+map.get("staus")+"\'");
        }
        if (map.get("msg")!=null){
            map.put("msg","\'"+map.get("msg")+"\'");
        }
        if (map.get("tradeNo")!=null&&map.get("tradeNo")!=""){
            map.put("tradeNo","\'"+map.get("tradeNo")+"\'");
        }
        if (map.get("userId")!=null&&map.get("userId")!=""){
            map.put("userId","\'"+map.get("userId")+"\'");
        }

        return map;
    }
}
