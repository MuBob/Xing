package com.example.wjx.xing.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class DefaultDaysList {
    public static List<String> instanceHalfOfDay=new ArrayList<>();
    public static List<String> instanceOneOfDay=new ArrayList<>();
    static {
        for (double i = 0; i <= 31; i+=0.5) {
            instanceHalfOfDay.add(String.valueOf(i));
            if((10*i)%2==0){
                instanceOneOfDay.add(String.valueOf(i));
            }
        }
    }
}
