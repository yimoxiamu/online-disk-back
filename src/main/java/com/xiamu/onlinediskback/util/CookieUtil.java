package com.xiamu.onlinediskback.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    /**
     * 设置cokkie
     * @param response
     * @param name cookie名字
     * @param value cookie值
     * @param maxAge cookie最大生命周期
     */
    public  void addCookie(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie=new Cookie(name,value);
        cookie.setPath("/");
        if(maxAge>0)
            cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 根据name获得值
     * @param request
     * @param name cookie名
     * @return
     */
    public  Cookie getCookieByName(HttpServletRequest request, String name){
        Map<String,Cookie> map=ReadCookieMap(request);
        if(map.containsKey(name)){
            Cookie cookie=(Cookie) map.get(name);
            return cookie;
        }else {
            return null;
        }
    }


    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private  Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
                System.out.println(cookie.getName()+cookie.getValue());
            }
        }
        return cookieMap;
    }
}
