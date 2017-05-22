package com.example.wjx.xing.net;

import com.example.wjx.xing.Common;
import com.example.wjx.xing.utils.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/5/18.
 */

public class RequestPath {
    private static final String LOGIN=Common.baseurl+"LoginServlet?id=%s&pwd=%s";
    private static final String SELF_INFO_BASE_GET=Common.baseurl+"SelfInfoBaseGetServlet?id=%s";
    private static final String SELF_INFO_BASE_MODIFY=Common.baseurl+"SelfInfoBaseModifyServlet?id=%s";
    //登陆接口
    public static String getLogin(String id, String pwd){
        return String.format(LOGIN, id, pwd);
    }
    public static String getSelfInfoBase(String id){
        return String.format(SELF_INFO_BASE_GET, id);
    }
    public static String getModifySelfInfoBase(
            String id, String name, String sex, String idCard, String birthday){
        StringBuffer url = new StringBuffer(String.format(SELF_INFO_BASE_MODIFY, id));
        if(!StringUtil.isNull(name)){
            url.append("&name="+getUrlEncode(name));
        }
        if(!StringUtil.isNull(sex)){
            url.append("&sex="+getUrlEncode(sex));
        }
        if(!StringUtil.isNull(idCard)){
            url.append("&idCard="+getUrlEncode(idCard));
        }
        if(!StringUtil.isNull(birthday)){
            url.append("&birthday="+getUrlEncode(birthday));
        }
        return url.toString();
    }

    private static String getUrlEncode(String value) {
        try {
            value = URLEncoder.encode(value, "utf-8"); //先对中文进行UTF-8编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    //日常考勤，签到
    public static final String EVERY_DAY_SIGN_IN= Common.baseurl + "EverydayServlet?";
    //日常考勤，签退
    public static final String EVERY_DAY_SIGN_OUT= Common.baseurl + "EverydayServlet?";
}
