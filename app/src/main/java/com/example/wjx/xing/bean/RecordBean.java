package com.example.wjx.xing.bean;

/**
 * Created by Administrator on 2017/5/18.
 */

public class RecordBean {
    private String name;
    private String id;

    @Override
    public String toString() {
        return "RecordBean{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
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
}
