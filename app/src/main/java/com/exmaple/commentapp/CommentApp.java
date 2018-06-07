package com.exmaple.commentapp;

import android.app.Application;

import io.realm.Realm;

public class CommentApp extends Application {

    public String userName = "";

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
