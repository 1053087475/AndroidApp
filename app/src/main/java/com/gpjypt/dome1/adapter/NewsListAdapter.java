package com.gpjypt.dome1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gpjypt.dome1.R;
import com.gpjypt.dome1.bean.MNewsBean;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MViewHolder> {
    ArrayList<MNewsBean.ResultBean.DataBean> dataBeans;
    ClickItemCallBack<MNewsBean.ResultBean.DataBean> clickItemCallBack;

    public NewsListAdapter(ArrayList<MNewsBean.ResultBean.DataBean> dataBeans) {
        this.dataBeans = dataBeans;
    }

    public void addClickItemListner(ClickItemCallBack<MNewsBean.ResultBean.DataBean> clickItemCallBack) {
        this.clickItemCallBack = clickItemCallBack;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false);
        MViewHolder mViewHolder = new MViewHolder(view);


        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {


        //对应的item的布局view
        View view = holder.itemView;
        //对应的item的数据
        MNewsBean.ResultBean.DataBean dataBean = dataBeans.get(position);
        //item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickItemCallBack != null)
                    clickItemCallBack.clickItem(dataBean, position);
            }
        });
        //标题
        TextView tvNewsTitle = view.findViewById(R.id.tvNewsTitle);
        tvNewsTitle.setText(dataBean.getTitle());
        //日期
        TextView tvTime = view.findViewById(R.id.tvTime);
        tvTime.setText(dataBean.getDate());
        //作者
        TextView tvAuther = view.findViewById(R.id.tvAuther);
        tvAuther.setText("作者：" + dataBean.getAuthor_name());
        //图片
        ImageView ivPic = view.findViewById(R.id.ivPic);
        Glide.with(ivPic).load(dataBean.getThumbnail_pic_s()).into(ivPic);
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {


        public MViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface ClickItemCallBack<T> {
        void clickItem(T dataBean, int postion);
    }
}
