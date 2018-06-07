package com.exmaple.commentapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CommentsList extends RealmObject {

    @PrimaryKey
    String feedKey;

    RealmList<Messages> data;

    public String getFeedKey() {
        return feedKey;
    }

    public void setFeedKey(String feedKey) {
        this.feedKey = feedKey;
    }

    public RealmList<Messages> getData() {
        return data;
    }

    public void setData(RealmList<Messages> data) {
        this.data = data;
    }
}
