package com.example.wjx.xing;

/**
 * Created by youzi on 2017/3/13.
 */

public class Common {
    //后台服务器地址
    public final static String baseurl = "http://192.168.0.102:8080/Xing/";

    /**
     * 界面传值或在SharedPreferences中使用的Key
     */
    public final static String SP_NAME_SETTING="Seting_ordering";
    //标记用户是否登陆，及登陆身份，0=未登录，1=已登录&员工身份，2=已登录&管理员身份
    public final static String KEY_INT_ROLE_LOGIN="isLogin_Role";
    public final static String KEY_CURRENT_ONID="current_online_id";
    public final static String KEY_REPLY_TYPE="reply_type_evection1_leave2";
    public final static int REPLY_TYPE_EVECTION=1;
    public final static int REPLY_TYPE_LEAVE=2;

}
