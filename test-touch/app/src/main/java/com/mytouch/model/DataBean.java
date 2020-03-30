package com.mytouch.model;

import java.util.List;

/**
 * 测试数据的结构
 */
public class DataBean {

    public int type;  // 0 默认的列表条目   1嵌套的列表
    public String title;

    public List<ChildDataBean> list;

    public static class ChildDataBean{
        public String name;
    }

}
