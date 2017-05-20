package com.example.wjx.xing.data;

import com.example.wjx.xing.bean.RoleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class DefaultRoleList {
    public static List<RoleBean> instance=new ArrayList<>();
    static {
        RoleBean roleBean1=new RoleBean();
        RoleBean roleBean2=new RoleBean();
        RoleBean roleBean3=new RoleBean();
        RoleBean roleBean4=new RoleBean();
        roleBean1.setName("项目负责人");
        roleBean2.setName("会计师");
        roleBean3.setName("技术员");
        roleBean4.setName("设计员");
        roleBean1.setId("10001");
        roleBean2.setId("10002");
        roleBean3.setId("10003");
        roleBean4.setId("10004");
        instance.add(roleBean1);
        instance.add(roleBean2);
        instance.add(roleBean3);
        instance.add(roleBean4);
    }
}
