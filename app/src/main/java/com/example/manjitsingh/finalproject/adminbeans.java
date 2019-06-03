package com.example.manjitsingh.finalproject;

import java.io.Serializable;

/**
 * Created by manjitsingh on 2017-12-11.
 */

public class adminbeans implements Serializable {
    private long id;
    private String userid;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
