package com.example.hasee.shoppingdemo.Bean;

/**
 * Created by Lmz on 2019/05/21
 */
public class User {
    String id;
    String name;
    String pwd;
    String avatar;

    public User(String id, String name, String pwd, String avatar) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.avatar = avatar;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
