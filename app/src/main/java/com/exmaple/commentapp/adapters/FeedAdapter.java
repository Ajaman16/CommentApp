package com.exmaple.commentapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exmaple.commentapp.R;
import com.exmaple.commentapp.utils.SquareImageView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;

    ArrayList<HashMap<String, Object>> data;


    public FeedAdapter(Context context, final ArrayList<HashMap<String, Object>> data)
    {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_adapter, parent, false);
        return new FeedAdapter.ViewHolderMain(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolderMain) {

            final ViewHolderMain userViewHolder = (ViewHolderMain) holder;


            final HashMap<String, Object> map = data.get(position);

            if (map != null) {

                userViewHolder.userName.setText(""+map.get("userName"));

                CharSequence timeAgo = DateUtils.getRelativeTimeSpanString((long)map.get("timestamp"),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
                userViewHolder.timestamp.setText(""+timeAgo);

                Glide.with(context).load("file:///android_asset/" + map.get("image")).into(userViewHolder.image);


            }
        }


    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class ViewHolderMain extends RecyclerView.ViewHolder
    {
        @BindView(R.id.user_name)
        TextView userName;

        @BindView(R.id.timestamp)
        TextView timestamp;

        @BindView(R.id.content_image)
        SquareImageView image;

        @BindView(R.id.comment_button)
        ImageView commentButton;

        public ViewHolderMain(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
