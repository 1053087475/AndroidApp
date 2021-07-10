package com.gpjypt.dome1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gpjypt.dome1.HomeActivity;
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

public class GuoNeiFragment extends BaseFragment {
    int page = 1;
    int pageSize = 30;
    ArrayList<MNewsBean.ResultBean.DataBean> dataBeans = new ArrayList<>();
    NewsListAdapter newsListAdapter;
    String type = "guonei";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guonei, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String s = bundle.getString(HomeActivity.FRAGEMNT_NAME_KEY);
        if (StaticConfig.tag_wd.equals(s)) {
            return;
        } else {
            type = s;
        }
        initView();
        getData();
    }

    private void initView() {
        getView().findViewById(R.id.ivBack).setVisibility(View.GONE);
        TextView tvTitle = getView().findViewById(R.id.tvTitle);
        tvTitle.setText(getTitle(type));

        RecyclerView rvNewsList = getView().findViewById(R.id.rvNewsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvNewsList.setLayoutManager(linearLayoutManager);
        newsListAdapter = new NewsListAdapter(dataBeans);
        rvNewsList.setAdapter(newsListAdapter);
        //列表的item的点击事件
        newsListAdapter.addClickItemListner(new NewsListAdapter.ClickItemCallBack<MNewsBean.ResultBean.DataBean>() {
            @Override
            public void clickItem(MNewsBean.ResultBean.DataBean dataBean, int postion) {
                Intent mIntent = new Intent(getContext(), NewsDetailsActivity.class);

                mIntent.putExtra(NewsDetailsActivity.UNIQ_KEY, dataBean.getUniquekey());
                startActivity(mIntent);
            }
        });
    }

    private void getData() {
        HttpHelper.create(NewsApi.class)
                .getGuoNeiNewsList(type, StaticConfig.NEWS_KEY,
                        page, pageSize, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MNewsBean.ResultBean>() {
                    @Override
                    public void onHandlerSuccess(MNewsBean.ResultBean value) {
                        if (value != null && !value.getData().isEmpty()) {
                            dataBeans.clear();
                            dataBeans.addAll(value.getData());
                            newsListAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    private String getTitle(String type) {
        if (type.equals(StaticConfig.tag_gn)) {
            return getString(R.string.tab_name_gn);
        } else if (type.equals(StaticConfig.tag_gj)) {
            return getString(R.string.tab_name_gj);
        } else if (type.equals(StaticConfig.tag_yl)) {
            return getString(R.string.tab_name_yl);
        } else if (type.equals(StaticConfig.tag_ty)) {
            return getString(R.string.tab_name_ty);
        } else return getString(R.string.tab_name_gn);
    }
}
