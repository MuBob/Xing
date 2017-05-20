package com.example.wjx.xing.data;

import com.example.wjx.xing.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class DefaultRecordList {
    public static List<RecordBean> instance=new ArrayList<>();
    static {
        RecordBean bean1=new RecordBean();
        RecordBean bean2=new RecordBean();
        RecordBean bean3=new RecordBean();
        RecordBean bean4=new RecordBean();
        bean1.setName("博士");
        bean2.setName("硕士");
        bean3.setName("双学位/学士");
        bean4.setName("学士");
        bean1.setId("2001");
        bean2.setId("2002");
        bean3.setId("2003");
        bean4.setId("2004");
        instance.add(bean1);
        instance.add(bean2);
        instance.add(bean3);
        instance.add(bean4);
    }
}
