package com.exmaple.commentapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exmaple.commentapp.adapters.FeedAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends BaseActivity {

    Context context;
    @BindView(R.id.recyclerview_feed)
    RecyclerView recyclerViewFeed;

    FeedAdapter adapter;

    ArrayList<HashMap<String, Object>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);

        context = this;

        recyclerViewFeed.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FeedAdapter(context, data);
        recyclerViewFeed.setAdapter(adapter);

        //Toast.makeText(context, getUserName(), Toast.LENGTH_LONG).show();
        
        setUpDummyData();
    }

    private void setUpDummyData() {

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("userName", "Aman Jain");
        map1.put("timestamp", 1528361258000l);
        map1.put("image", "image1.jpg");
        map1.put("feedKey", "1");
        data.add(map1);

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("userName", "Aman Jain");
        map2.put("timestamp", 1528360842000l);
        map2.put("image", "image2.jpg");
        map2.put("feedKey", "2");
        data.add(map2);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you want to exit this application?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing

                    }
                })
                .show();
    }
}
