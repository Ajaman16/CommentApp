package com.exmaple.commentapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.exmaple.commentapp.adapters.CommentsAdapter;
import com.exmaple.commentapp.model.CommentsList;
import com.exmaple.commentapp.model.Messages;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

public class CommentActivity extends BaseActivity {

    Context context;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.comment_text)
    EditText commentText;
    @BindView(R.id.send_button)
    ImageView sendButton;

    String feedKey = "", userName;

    CommentsAdapter adapter;
    ArrayList<HashMap<String, Object>> data = new ArrayList<>();
    RealmList<Messages> dataList = new RealmList<>();

    Realm realm;

    CommentsList comments;

    boolean isNew = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        context = this;
        realm = Realm.getDefaultInstance();

        userName = getUserName();

        if(getIntent().getStringExtra("feedKey")!= null)
        {
            feedKey = getIntent().getStringExtra("feedKey");

            //Toast.makeText(context, feedKey, Toast.LENGTH_LONG).show();
        }

        /*commentText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(commentText, InputMethodManager.SHOW_IMPLICIT);*/

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("CommentsList");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        sendButton.setAlpha(0.4f);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len = commentText.getText().length();

                if(len != 0)
                {
                    /*HashMap<String, Object> d = new HashMap<String, Object>();
                    String cKey = ""+((long)Math.pow(10, 16) - System.currentTimeMillis());

                    d.put("message", commentText.getText().toString());
                    d.put("userName", userName);
                    d.put("timestamp", System.currentTimeMillis());*/

                    Messages messages = new Messages();
                    messages.setMessage(commentText.getText().toString());
                    messages.setUserName(userName);
                    messages.setTimestamp(System.currentTimeMillis());

                    commentText.setText("");

                    //data.add(0, d);
                    dataList.add(0, messages);
                    adapter.notifyDataSetChanged();

                    //Toast.makeText(context, ""+dataList.size(), Toast.LENGTH_LONG).show();

                    //recyclerView.scrollToPosition(data.size()-1);

                }
            }
        });

        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int len = editable.length();

                if(len == 0)
                {
                    sendButton.setAlpha(0.4f);
                }
                else
                {
                    sendButton.setAlpha(1.0f);
                }

            }
        });


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new CommentsAdapter(this, dataList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();

        comments = realm.where(CommentsList.class).equalTo("feedKey", feedKey).findFirst();


        if(comments == null)
        {
            isNew = true;
            //Toast.makeText(context, "null", Toast.LENGTH_LONG).show();
            comments = new CommentsList();
            comments.setFeedKey(feedKey);
        }
        else
        {
            isNew = false;
            //Toast.makeText(context, "exist", Toast.LENGTH_LONG).show();
            dataList.clear();
            dataList.addAll(comments.getData());
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onPause() {

        realm.beginTransaction();
        if(isNew) {

            comments.setData(dataList);
            realm.copyToRealmOrUpdate(comments);

        }
        else
        {
            comments.getData().clear();
            comments.getData().addAll(0, dataList);
            realm.copyToRealmOrUpdate(comments);
        }
        realm.commitTransaction();

        super.onPause();
    }
}
