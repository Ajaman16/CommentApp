package com.exmaple.commentapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exmaple.commentapp.R;
import com.exmaple.commentapp.model.Messages;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;


public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    ArrayList<HashMap<String, Object>> data;
    RealmList<Messages> dataList = new RealmList<>();


    public CommentsAdapter(Context context, ArrayList<HashMap<String, Object>> data) {
        this.context = context;
        this.data = data;
    }

    public CommentsAdapter(Context context, RealmList<Messages> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_feed_adapter, parent, false);
        return new CommentsAdapter.ViewHolderMain(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderMain) {
            if (dataList.size() != 0) {
                ViewHolderMain mHolder = (ViewHolderMain) holder;

                //HashMap<String, Object> map = data.get(position);
                Messages messages = dataList.get(position);

                mHolder.userName.setText(messages.getUserName());

                CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(messages.getTimestamp(),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
                mHolder.timestamp.setText("" + timeAgo);

                mHolder.message.setText(messages.getMessage());


            }
        }


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }


    public class ViewHolderMain extends RecyclerView.ViewHolder
    {
        @BindView(R.id.user_name_comment)
        TextView userName;

        @BindView(R.id.timestamp)
        TextView timestamp;

        @BindView(R.id.message)
        TextView message;

        public ViewHolderMain(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
