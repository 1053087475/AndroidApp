package com.gpjypt.dome1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gpjypt.dome1.R;
import com.gpjypt.dome1.bean.NewsItem;

import java.util.ArrayList;

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.ListHolder> {
    ArrayList<NewsItem> newsItems = new ArrayList<>();

    public TestListAdapter(ArrayList<NewsItem> newsItems) {
        this.newsItems.clear();
        this.newsItems.addAll(newsItems);
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_news_list, parent, false);
        ListHolder listHolder = new ListHolder(view);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        View view = holder.itemView;
        NewsItem item=newsItems.get(position);
        TextView textView = view.findViewById(R.id.tvAuther);
        textView.setText("作者：" + item.getAuthor_name());
        TextView tvNewsTitle=view.findViewById(R.id.tvNewsTitle);
        tvNewsTitle.setText(item.getTitle());
        TextView tvTime=view.findViewById(R.id.tvTime);
        tvTime.setText(item.getDate());
        ImageView ivPic=view.findViewById(R.id.ivPic);
        Glide.with(ivPic).load(item.getUrl()).into(ivPic);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    class ListHolder extends RecyclerView.ViewHolder {

        public ListHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
