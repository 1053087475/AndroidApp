package com.gpjypt.dome1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gpjypt.dome1.R;
import com.gpjypt.dome1.activity.NewsDetailsActivity;
import com.gpjypt.dome1.adapter.NewsListAdapter;
import com.gpjypt.dome1.api.NewsApi;
import com.gpjypt.dome1.base.BaseFragment;
import com.gpjypt.dome1.bean.MNewsBean;
import com.gpjypt.dome1.http.BaseObserver;
import com.gpjypt.dome1.http.HttpHelper;
import com.gpjypt.dome1.utils.StaticConfig;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShouYeChildFragment extends BaseFragment {
    public static final String TAB_NAME_KEY = "key_tab_name";

    NewsListAdapter newsListAdapter;
    ArrayList<MNewsBean.ResultBean.DataBean> dataBeans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shou_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        getData();

    }

    private void getData() {
        String tabType = getArguments().getString(TAB_NAME_KEY);
        HttpHelper.create(NewsApi.class)
                .getGuoNeiNewsList(tabType, StaticConfig.NEWS_KEY, 1, 30, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MNewsBean.ResultBean>() {

                    @Override
                    public void onHandlerSuccess(MNewsBean.ResultBean value) {
                        if (value != null && !value.getData().isEmpty()) {
                            dataBeans.clear();
                            dataBeans.addAll(value.getData());
                            //通知适配器数据更新
                            newsListAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initView() {
        RecyclerView rvNewsList = getView().findViewById(R.id.rvNewsList);
        rvNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsListAdapter = new NewsListAdapter(dataBeans);
        rvNewsList.setAdapter(newsListAdapter);
        newsListAdapter.addClickItemListner(new NewsListAdapter.ClickItemCallBack<MNewsBean.ResultBean.DataBean>(){


            @Override
            public void clickItem(MNewsBean.ResultBean.DataBean dataBean, int postion) {
                Intent mIntent = new Intent(getContext(), NewsDetailsActivity.class);

                mIntent.putExtra(NewsDetailsActivity.UNIQ_KEY, dataBean.getUniquekey());
                startActivity(mIntent);
            }
        });

    }
}
