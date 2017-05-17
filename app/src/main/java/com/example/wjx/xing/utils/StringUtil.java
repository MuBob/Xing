package com.example.wjx.xing.utils;

/**
 * Created by Administrator on 2017/5/17.
 */

public class StringUtil {

    /**
     * 验证password是否正确
     * 模拟密码大于6位
     * @param password
     */
    public static boolean isPassword(String password) {
        return password.length()>=6;
    }

    /**
     * 验证number是否正确
     * 当前情形  需要工号为学号   eg:201310644
     * @param number
     */
    public static boolean isNumber(String number) {
        return number.length() ==9;
    }

}
