package com.example.wjx.xing.utils;

import com.example.wjx.xing.Common;

/**
 * Created by Administrator on 2017/5/18.
 */

public class RequestPath {
    private static final String LOGIN=Common.baseurl+"LoginServlet?id=%s&pwd=%s";
    //登陆接口
    public static String getLogin(String id, String pwd){
        return String.format(LOGIN, id, pwd);
    }
    //日常考勤，签到
    public static final String EVERY_DAY_SIGN_IN= Common.baseurl + "EverydayServlet?";
    //日常考勤，签退
    public static final String EVERY_DAY_SIGN_OUT= Common.baseurl + "EverydayServlet?";
}
