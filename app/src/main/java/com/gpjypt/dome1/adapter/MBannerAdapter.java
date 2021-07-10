package com.gpjypt.dome1.adapter;

import android.text.TextUtils;
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
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class MBannerAdapter extends BannerAdapter<MNewsBean.ResultBean.DataBean, MBannerAdapter.BannerHolder> {


    public MBannerAdapter(List<MNewsBean.ResultBean.DataBean> datas) {
        super(datas);
    }

    @Override
    public BannerHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new BannerHolder(view);
    }

    @Override
    public void onBindView(BannerHolder holder, MNewsBean.ResultBean.DataBean data, int position, int size) {
        ImageView ivBanner = holder.itemView.findViewById(R.id.ivBanner);
        if (!TextUtils.isEmpty(data.getThumbnail_pic_s()))
            Glide.with(ivBanner).load(data.getThumbnail_pic_s()).into(ivBanner);
        TextView tvTextContent = holder.itemView.findViewById(R.id.tvTextContent);
        tvTextContent.setText(data.getTitle());
    }

    class BannerHolder extends RecyclerView.ViewHolder {

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
