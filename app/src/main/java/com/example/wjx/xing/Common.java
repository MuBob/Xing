package com.example.wjx.xing;

/**
 * Created by youzi on 2017/3/13.
 */

public class Common {
    //后台服务器地址
    public final static String baseurl = "http://192.168.56.1:8080/Xing/";

    /**
     * SharedPreferences中使用的Key
     */
    public final static String SP_NAME_SETTING="Seting_ordering";
    //标记用户是否登陆，及登陆身份，0=未登录，1=已登录&员工身份，2=已登录&管理员身份
    public final static String KEY_ROLE_LOGIN="isLogin_Role";


}
