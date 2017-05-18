package com.example.wjx.xing.bean;

/**
 * Created by Administrator on 2017/5/18.
 */

public class DepartmentBean {
    private String name;
    private String id;
    private String des;

    public DepartmentBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "DepartmentBean{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
