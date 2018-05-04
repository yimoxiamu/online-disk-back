package com.xiamu.onlinediskback.util;

import com.xiamu.onlinediskback.entity.PageEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageUtil {
    public static List<PageEntity> pageNum(Map map, String lists,int pagesize){
        int count = (Integer) map.get(lists);
        int pageCount;
        if (count % pagesize == 0) {
            pageCount = count / pagesize;
        } else {
            pageCount = count / pagesize + 1;
        }
        List list = new ArrayList();
        for (int i = 1; i <= pageCount; i++) {
            PageEntity pageEntity = new PageEntity();
            pageEntity.setCurrentPage(i);
            list.add(pageEntity);
        }
        return list;
    }
}
