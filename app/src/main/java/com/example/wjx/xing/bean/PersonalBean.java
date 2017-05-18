package com.example.wjx.xing.bean;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/18.
 */

public class PersonalBean {
    private String name;
    private String number;
    private DepartmentBean departmentBean;
    private RoleBean roleBean;
    private RecordBean recordBean;
    private SkillBean skillBean;
    private String password;
    private Date birthday;
    private int sex;  //性别，1=男性，2=女性

    @Override
    public String toString() {
        return "PersonalBean{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", departmentBean=" + departmentBean +
                ", roleBean=" + roleBean +
                ", recordBean=" + recordBean +
                ", skillBean=" + skillBean +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentBean getDepartmentBean() {
        return departmentBean;
    }

    public void setDepartmentBean(DepartmentBean departmentBean) {
        this.departmentBean = departmentBean;
    }

    public RoleBean getRoleBean() {
        return roleBean;
    }

    public void setRoleBean(RoleBean roleBean) {
        this.roleBean = roleBean;
    }

    public RecordBean getRecordBean() {
        return recordBean;
    }

    public void setRecordBean(RecordBean recordBean) {
        this.recordBean = recordBean;
    }

    public SkillBean getSkillBean() {
        return skillBean;
    }

    public void setSkillBean(SkillBean skillBean) {
        this.skillBean = skillBean;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}