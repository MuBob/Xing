package com.example.wjx.xing.net;

import com.example.wjx.xing.Common;
import com.example.wjx.xing.utils.DateUtil;
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
    private static final String SELF_INFO_RECORD_GET=Common.baseurl+"SelfInfoRecordGetServlet?id=%s";
    private static final String SELF_INFO_RECORD_MODIFY=Common.baseurl+"SelfInfoRecordModifyServlet?id=%s";
    private static final String SELF_INFO_SKILL_GET=Common.baseurl+"SelfInfoSkillGetServlet?id=%s";
    private static final String SELF_INFO_SKILL_MODIFY=Common.baseurl+"SelfInfoSkillModifyServlet?id=%s";
    private static final String PASSWORD_MODIFY=Common.baseurl+"PasswordModifyServlet?id=%s";
    public static final String EVERY_DAY_SIGN_IN= Common.baseurl + "SignInServlet?id=%s";
    public static final String EVERY_DAY_SIGN_OUT= Common.baseurl + "SignOutServlet?id=%s";


    public static String getLogin(String id, String pwd){
        return String.format(LOGIN, id, pwd);
    }
    public static String getSelfInfoBase(String id){
        return String.format(SELF_INFO_BASE_GET, getUrlEncode(id));
    }
    public static String getModifySelfInfoBase(
            String id, String name, String sex, String idCard, String birthday){
        StringBuffer url = new StringBuffer(String.format(SELF_INFO_BASE_MODIFY, getUrlEncode(id)));
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
    public static String getSelfInfoRecord(String id){
        return String.format(SELF_INFO_RECORD_GET, getUrlEncode(id));
    }
    public static String getModifySelfInfoRecord(
            String id, String highRecord, String graduateSchool){
        StringBuffer url = new StringBuffer(String.format(SELF_INFO_RECORD_MODIFY, getUrlEncode(id)));
        if(!StringUtil.isNull(highRecord)){
            url.append("&highRecord="+getUrlEncode(highRecord));
        }
        if(!StringUtil.isNull(graduateSchool)){
            url.append("&graduateSchool="+getUrlEncode(graduateSchool));
        }
        return url.toString();
    }
    public static String getSelfInfoSkill(String id){
        return String.format(SELF_INFO_SKILL_GET, getUrlEncode(id));
    }
    public static String getModifySelfInfoSkill(
            String id, int accounting, int computer,int civil,int safety){
        StringBuffer url = new StringBuffer(String.format(SELF_INFO_SKILL_MODIFY, getUrlEncode(id)));
        if(accounting>0){
            url.append("&accounting="+String.valueOf(accounting));
        }
        if(computer>0){
            url.append("&computer="+String.valueOf(computer));
        }
        if(civil>0){
            url.append("&civil="+String.valueOf(civil));
        }
        if(safety>0){
            url.append("&safety="+String.valueOf(safety));
        }
        return url.toString();
    }
    public static String getPasswordModify(String id, String oldPwd, String newPwd){
        StringBuffer url=new StringBuffer(String.format(PASSWORD_MODIFY, getUrlEncode(id)));
        url.append("&pwd_old="+String.valueOf(oldPwd));
        url.append("&pwd_new="+String.valueOf(newPwd));
        return url.toString();
    }
    public static String getEverydaySignIn(String id, String place, String time){
        StringBuffer url=new StringBuffer(String.format(EVERY_DAY_SIGN_IN, getUrlEncode(id)));
        if(!StringUtil.isNull(place)){
            url.append("&place="+getUrlEncode(place));
            url.append("&sign="+getUrlEncode("true"));
        }
        if(StringUtil.isNull(time)){
            time= DateUtil.getCurrentTime();
        }
        url.append("&time="+time);
        return url.toString();
    }

    public static String getEverydaySignOut(String id, String place, String time){
        StringBuffer url=new StringBuffer(String.format(EVERY_DAY_SIGN_OUT, getUrlEncode(id)));
        if(!StringUtil.isNull(place)){
            url.append("&place="+getUrlEncode(place));
            url.append("&sign="+getUrlEncode("true"));
        }
        if(StringUtil.isNull(time)){
            time= DateUtil.getCurrentTime();
        }
        url.append("&time="+time);
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
}
