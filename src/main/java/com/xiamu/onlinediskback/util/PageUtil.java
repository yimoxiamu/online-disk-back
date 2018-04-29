package com.xiamu.onlinediskback.util;

import com.xiamu.onlinediskback.entity.PageEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageUtil {
    public static List<PageEntity> pageNum(Map map, String lists){
        int count = (Integer) map.get(lists);
        int pageCount;
        if (count % 10 == 0) {
            pageCount = count / 10;
        } else {
            pageCount = count / 10 + 1;
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
