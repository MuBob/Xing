package com.example.wjx.xing.data;

import com.example.wjx.xing.bean.SkillBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class DefaultSkillList {
    public static List<SkillBean> instance=new ArrayList<>();
    static {
        SkillBean bean1=new SkillBean();
        SkillBean bean2=new SkillBean();
        SkillBean bean3=new SkillBean();
        SkillBean bean4=new SkillBean();
        bean1.setName("会计职称");
        bean2.setName("计算机技能");
        bean3.setName("注册土木工程师");
        bean4.setName("注册安全工程师");
        bean1.setId("3001");
        bean2.setId("3002");
        bean3.setId("3003");
        bean4.setId("3004");
        instance.add(bean1);
        instance.add(bean2);
        instance.add(bean3);
        instance.add(bean4);
    }
}
