package com.example.wjx.xing;

/**
 * Created by youzi on 2017/3/13.
 */

public class Common {
    public static String SERVICE_IP="192.168.0.123";
    public static String SERVICE_PORT="8080";
    //后台服务器地址
    public static String baseurl = String.format("http://%s:%s/Xing/", SERVICE_IP, SERVICE_PORT);

    /**
     * 界面传值或在SharedPreferences中使用的Key
     */
    public final static String SP_NAME_SETTING = "Seting_ordering";
    //标记用户是否登陆，及登陆身份，0=未登录，1=已登录&员工身份，2=已登录&管理员身份
    public final static String KEY_INT_ROLE_LOGIN = "isLogin_Role";
    public final static String KEY_CURRENT_ONID = "current_online_id";
    public final static String KEY_REPLY_TYPE = "reply_type_evection1_leave2";
    public final static int APPLY_TYPE_EVECTION = 1;
    public final static int APPLY_TYPE_LEAVE = 2;
    public final static int REPLY_TYPE_AGREE = 1;
    public final static int REPLY_TYPE_DEFINED= 2;


}
