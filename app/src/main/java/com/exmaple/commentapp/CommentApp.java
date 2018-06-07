package com.exmaple.commentapp;

import android.app.Application;

public class CommentApp extends Application {

    public String userName = "";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
