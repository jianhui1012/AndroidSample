package com.golike.myapplication.model;

/**
 * Created by admin on 2017/8/1.
 */

public class User {

    private String uId;

    private  String uName;

    private String uPwd;

    private String uType;

    private String uLevel;


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public String getuLevel() {
        return uLevel;
    }

    public void setuLevel(String uLevel) {
        this.uLevel = uLevel;
    }
}
