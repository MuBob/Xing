package com.example.wjx.xing.bean;

/**
 * Created by Administrator on 2017/5/18.
 */

public class SkillBean {
    private String name;
    private String id;
    private String level;

    @Override
    public String toString() {
        return "SkilBean{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", level='" + level + '\'' +
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
