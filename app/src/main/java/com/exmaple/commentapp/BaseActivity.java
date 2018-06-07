package com.exmaple.commentapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getUserName() {
        return ((CommentApp)getApplicationContext()).getUserName();
    }

    public void setUserName(String userName) {
        ((CommentApp)getApplicationContext()).setUserName(userName);
    }
}
