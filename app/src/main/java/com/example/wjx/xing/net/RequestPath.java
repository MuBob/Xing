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
    private static final String LOGIN = Common.baseurl + "LoginServlet?id=%s&pwd=%s";
    private static final String SELF_INFO_BASE_GET = Common.baseurl + "SelfInfoBaseGetServlet?id=%s";
    private static final String SELF_INFO_BASE_MODIFY = Common.baseurl + "SelfInfoBaseModifyServlet?id=%s";
    private static final String SELF_INFO_RECORD_GET = Common.baseurl + "SelfInfoRecordGetServlet?id=%s";
    private static final String SELF_INFO_RECORD_MODIFY = Common.baseurl + "SelfInfoRecordModifyServlet?id=%s";
    private static final String SELF_INFO_SKILL_GET = Common.baseurl + "SelfInfoSkillGetServlet?id=%s";
    private static final String SELF_INFO_SKILL_MODIFY = Common.baseurl + "SelfInfoSkillModifyServlet?id=%s";
    private static final String PASSWORD_MODIFY = Common.baseurl + "PasswordModifyServlet?id=%s";
    public static final String EVERY_DAY_SIGN_IN = Common.baseurl + "SignInServlet?id=%s";
    public static final String EVERY_DAY_SIGN_OUT = Common.baseurl + "SignOutServlet?id=%s";
    public static final String APPLY_LEAVE = Common.baseurl + "ApplyLeaveServlet?id=%s";
    public static final String APPLY_EVECTION = Common.baseurl + "ApplyTripServlet?id=%s";
    public static final String LIST_DEPARTMENT = Common.baseurl + "DepartmentListGetServlet";
    public static final String DELETE_DEPARTMENT = Common.baseurl + "DepartmentDelServlet?id=%s";
    public static final String ADD_DEPARTMENT = Common.baseurl + "DepartmentAddServlet?id=%s";
    public static final String LIST_PERSONAL = Common.baseurl + "PersonalListGetServlet";
    public static final String DELETE_PERSONAL = Common.baseurl + "PersonalDelServlet?id=%s";
    public static final String ADD_PERSONAL = Common.baseurl + "PersonalAddServlet?id=%s";
    public static final String LIST_PERSONAL_TURN = Common.baseurl + "PersonalTurnDepartmentListGetServlet";
    public static final String DELETE_PERSONAL_TURN = Common.baseurl + "PersonalTurnDepartmentDelServlet?id=%s";
    public static final String TURN_PERSONAL_DEPARTMENT = Common.baseurl + "PersonalTurnDepartmentServlet?id=%s";
    public static final String LIST_PERSONAL_ATTENDANCE = Common.baseurl + "AttendancePersonalListGetServlet";
    public static final String TURN_PERSONAL_ATTENDANCE = Common.baseurl + "AttendanceAddServlet?id=%s";
    public static final String LIST_PERSONAL_UNREPLY_TRIP = Common.baseurl + "UnReplyTripListGetServlet";
    public static final String LIST_PERSONAL_UNREPLY_LEAVE = Common.baseurl + "UnReplyLeaveListGetServlet";
    public static final String REPLY_TRIP = Common.baseurl + "ReplyTripServlet?id=%s";
    public static final String REPLY_LEAVE = Common.baseurl + "ReplyLeaveServlet?id=%s";

    public static String getLogin(String id, String pwd) {
        return String.format(LOGIN, id, pwd);
    }

    public static String getSelfInfoBase(String id) {
        return String.format(SELF_INFO_BASE_GET, getUrlEncode(id));
    }

    public static String getModifySelfInfoBase(
            String id, String name, String sex, String idCard, String birthday) {
        StringBuffer url = new StringBuffer(String.format(SELF_INFO_BASE_MODIFY, getUrlEncode(id)));
        if (!StringUtil.isNull(name)) {
            url.append("&name=" + getUrlEncode(name));
        }
        if (!StringUtil.isNull(sex)) {
            url.append("&sex=" + getUrlEncode(sex));
        }
        if (!StringUtil.isNull(idCard)) {
            url.append("&idCard=" + getUrlEncode(idCard));
        }
        if (!StringUtil.isNull(birthday)) {
            url.append("&birthday=" + getUrlEncode(birthday));
        }
        return url.toString();
    }

    public static String getSelfInfoRecord(String id) {
        return String.format(SELF_INFO_RECORD_GET, getUrlEncode(id));
    }

    public static String getModifySelfInfoRecord(
            String id, String highRecord, String graduateSchool) {
        StringBuffer url = new StringBuffer(String.format(SELF_INFO_RECORD_MODIFY, getUrlEncode(id)));
        if (!StringUtil.isNull(highRecord)) {
            url.append("&highRecord=" + getUrlEncode(highRecord));
        }
        if (!StringUtil.isNull(graduateSchool)) {
            url.append("&graduateSchool=" + getUrlEncode(graduateSchool));
        }
        return url.toString();
    }

    public static String getSelfInfoSkill(String id) {
        return String.format(SELF_INFO_SKILL_GET, getUrlEncode(id));
    }

    public static String getModifySelfInfoSkill(
            String id, int accounting, int computer, int civil, int safety) {
        StringBuffer url = new StringBuffer(String.format(SELF_INFO_SKILL_MODIFY, getUrlEncode(id)));
        if (accounting > 0) {
            url.append("&accounting=" + String.valueOf(accounting));
        }
        if (computer > 0) {
            url.append("&computer=" + String.valueOf(computer));
        }
        if (civil > 0) {
            url.append("&civil=" + String.valueOf(civil));
        }
        if (safety > 0) {
            url.append("&safety=" + String.valueOf(safety));
        }
        return url.toString();
    }

    public static String getPasswordModify(String id, String oldPwd, String newPwd) {
        StringBuffer url = new StringBuffer(String.format(PASSWORD_MODIFY, getUrlEncode(id)));
        url.append("&pwd_old=" + String.valueOf(oldPwd));
        url.append("&pwd_new=" + String.valueOf(newPwd));
        return url.toString();
    }

    public static String getEverydaySignIn(String id, String place, String time) {
        StringBuffer url = new StringBuffer(String.format(EVERY_DAY_SIGN_IN, getUrlEncode(id)));
        if (!StringUtil.isNull(place)) {
            url.append("&place=" + getUrlEncode(place));
        }
        //根据time是否为空，来判断是获取信息列表，还是签到
        if (StringUtil.isNull(time)) {
            time = DateUtil.getCurrentTime();
        } else {
            url.append("&sign=" + getUrlEncode("true"));
        }
        url.append("&time=" + time);
        return url.toString();
    }

    public static String getEverydaySignOut(String id, String place, String time) {
        StringBuffer url = new StringBuffer(String.format(EVERY_DAY_SIGN_OUT, getUrlEncode(id)));
        if (!StringUtil.isNull(place)) {
            url.append("&place=" + getUrlEncode(place));
            url.append("&sign=" + getUrlEncode("true"));
        }
        if (StringUtil.isNull(time)) {
            time = DateUtil.getCurrentTime();
        }
        url.append("&time=" + time);
        return url.toString();
    }

    public static String getApplyLeave(String id, String days, String reason, String time, String picture, String startDay, String endDay) {
        StringBuffer url = new StringBuffer(String.format(APPLY_LEAVE, getUrlEncode(id)));
        if (!StringUtil.isNull(days)) {
            url.append("&days=" + getUrlEncode(days));
        }
        if (!StringUtil.isNull(startDay) && !StringUtil.isNull(endDay)) {
            url.append("&startDay=" + getUrlEncode(startDay));
            url.append("&endDay=" + getUrlEncode(endDay));
        }
        url.append("&reason=" + getUrlEncode(reason));
        if (StringUtil.isNull(time)) {
            time = DateUtil.getCurrentTime();
        }
        url.append("&time=" + time);
        if (!StringUtil.isNull(picture)) {
            url.append("&picture=" + getUrlEncode(picture));
        }
        return url.toString();
    }


    public static String getApplyEvection(String id, String days, String place, String way, String time, String matters, String startDay, String endDay) {
        StringBuffer url = new StringBuffer(String.format(APPLY_EVECTION, getUrlEncode(id)));
        if (!StringUtil.isNull(days)) {
            url.append("&days=" + getUrlEncode(days));
        }
        if (!StringUtil.isNull(startDay) && !StringUtil.isNull(endDay)) {
            url.append("&startDay=" + getUrlEncode(startDay));
            url.append("&endDay=" + getUrlEncode(endDay));
        }
        url.append("&place=" + getUrlEncode(place));
        url.append("&way=" + getUrlEncode(way));
        if (!StringUtil.isNull(matters)) {
            url.append("&matters=" + getUrlEncode(matters));
        }
        if (StringUtil.isNull(time)) {
            time = DateUtil.getCurrentTime();
        }
        url.append("&time=" + time);
        return url.toString();
    }

    public static String getListDepartment() {
        return LIST_DEPARTMENT;
    }

    public static String getDeleteDepartment(String id) {
        return String.format(DELETE_DEPARTMENT, getUrlEncode(id));
    }

    public static String getAddDepartment(String id, String name, String desc) {
        StringBuffer url = new StringBuffer(String.format(ADD_DEPARTMENT, getUrlEncode(id)));
        url.append("&name=" + getUrlEncode(name));
        url.append("&desc=" + getUrlEncode(desc));
        return url.toString();
    }

    public static String getListPersonal(String dId) {
        if (StringUtil.isNull(dId)) {
            return LIST_PERSONAL;
        } else {
            StringBuffer url = new StringBuffer(LIST_PERSONAL);
            url.append("?departmentId=" + getUrlEncode(dId));
            return url.toString();
        }
    }

    public static String getDeletePersonal(String id) {
        return String.format(DELETE_PERSONAL, getUrlEncode(id));
    }

    public static String getAddPersonal(
            String id, String name, String pwd, String departmentId, String sex, String email, String highRecord, String title, String role) {
        StringBuffer url = new StringBuffer(String.format(ADD_PERSONAL, getUrlEncode(id)));
        url.append("&name=" + getUrlEncode(name));
        url.append("&pwd=" + getUrlEncode(pwd));
        url.append("&departmentId=" + getUrlEncode(departmentId));
        url.append("&sex=" + getUrlEncode(sex));
        url.append("&email=" + getUrlEncode(email));
        url.append("&highRecord=" + getUrlEncode(highRecord));
        url.append("&title=" + getUrlEncode(title));
        url.append("&role=" + getUrlEncode(role));
        return url.toString();
    }

    public static String getDeletePersonalTurn(String id) {
        return String.format(DELETE_PERSONAL_TURN, getUrlEncode(id));
    }

    public static String getTurnPersonalDepartment(
            String id, String departmentId, String reason) {
        StringBuffer url = new StringBuffer(String.format(TURN_PERSONAL_DEPARTMENT, getUrlEncode(id)));
        url.append("&departmentId=" + getUrlEncode(departmentId));
        url.append("&reason=" + getUrlEncode(reason));
        url.append("&time=" + DateUtil.getCurrentTime());
        return url.toString();
    }

    public static String getListPersonalTurn(String id) {
        if(StringUtil.isNull(id)){
            return LIST_PERSONAL_TURN;
        }else {
            StringBuffer url = new StringBuffer(LIST_PERSONAL_TURN);
            url.append("?id=" + getUrlEncode(id));
            return url.toString();
        }
    }

    public static String getListPersonalAttendance(String dId) {
        if (StringUtil.isNull(dId)) {
            return LIST_PERSONAL_ATTENDANCE;
        } else {
            StringBuffer url = new StringBuffer(LIST_PERSONAL_ATTENDANCE);
            url.append("?departmentId=" + getUrlEncode(dId));
            return url.toString();
        }
    }


    public static String getAddAttendance(String id, double tripDays, double leaveDays, double absenteeismDays) {
        StringBuffer url = new StringBuffer(String.format(TURN_PERSONAL_ATTENDANCE, getUrlEncode(id)));
        if (tripDays >= 0) {
            url.append("&tripDays=" + tripDays);
        }
        if (leaveDays >= 0) {
            url.append("&leaveDays=" + leaveDays);
        }
        if (absenteeismDays >= 0) {
            url.append("&absenteeismDays=" + absenteeismDays);
        }
        return url.toString();
    }

    public static String getListPersonalUnreplyTrip(){
        return LIST_PERSONAL_UNREPLY_TRIP;
    }
    public static String getListPersonalUnreplyLeave(){
        return LIST_PERSONAL_UNREPLY_LEAVE;
    }
    public static String getReplyTrip(String id, int reply, String reason, String time){
        StringBuffer url = new StringBuffer(String.format(REPLY_TRIP, getUrlEncode(id)));
        url.append("&reply=" + reply);
        url.append("&reason=" + getUrlEncode(reason));
        url.append("&time=" + time);
        return url.toString();
    }
    public static String getReplyLeave(String id, int reply, String reason, String time){
        StringBuffer url = new StringBuffer(String.format(REPLY_LEAVE, getUrlEncode(id)));
        url.append("&reply=" + reply);
        url.append("&reason=" + getUrlEncode(reason));
        url.append("&time=" + time);
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
