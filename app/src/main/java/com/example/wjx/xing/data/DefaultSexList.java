package com.example.wjx.xing.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class DefaultSexList {
    public static List<String> instance=new ArrayList<>();
    static {
        instance.add("男");
        instance.add("女");
    }
}
