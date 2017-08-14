package com.golike.customviews.model;

/**
 * Created by admin on 2017/8/14.
 */

public class TextMessage{

    private String content;
    private String time;
    private UserInfo userInfo;

    public static TextMessage obtain(String text) {
        TextMessage model = new TextMessage();
        model.setContent(text);
        return model;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
