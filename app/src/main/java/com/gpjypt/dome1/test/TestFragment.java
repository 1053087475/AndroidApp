package com.gpjypt.dome1.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gpjypt.dome1.R;
import com.gpjypt.dome1.adapter.TestListAdapter;
import com.gpjypt.dome1.bean.NewsItem;
import com.gpjypt.dome1.http.BaseObserver;
import com.gpjypt.dome1.http.HttpHelper;
import com.gpjypt.dome1.utils.StaticConfig;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        HttpHelper.create(MApi.class)
                .getNewsIndex("top", StaticConfig.NEWS_KEY,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>(){
                    @Override
                    public void onHandlerSuccess(Object value) {

                    }
                });

        ArrayList<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(new NewsItem("“新时代女性的自我关爱”主题沙龙暨双山街道福泰社区妇儿活动家园启动仪式举行",
                "2021-03-08 13:47:00",
                "鲁网",
                "https://dfzximg02.dftoutiao.com/news/20210308/20210308133849_b9f3d069a1ab400bf2d87fcc15793ca5_1_mwpm_03201609.png"
        ));
        recyclerView.setLayoutManager(linearLayoutManager);
        TestListAdapter listAdapter = new TestListAdapter(newsItems);
        recyclerView.setAdapter(listAdapter);


    }


}
