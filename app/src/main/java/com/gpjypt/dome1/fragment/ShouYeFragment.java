package com.gpjypt.dome1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.gpjypt.dome1.R;
import com.gpjypt.dome1.activity.NewsDetailsActivity;
import com.gpjypt.dome1.adapter.IndiactorViewPagerAdapter;
import com.gpjypt.dome1.adapter.MBannerAdapter;
import com.gpjypt.dome1.api.NewsApi;
import com.gpjypt.dome1.base.BaseFragment;
import com.gpjypt.dome1.bean.MNewsBean;
import com.gpjypt.dome1.http.BaseObserver;
import com.gpjypt.dome1.http.HttpHelper;
import com.gpjypt.dome1.utils.StaticConfig;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.gpjypt.dome1.HomeActivity.FRAGEMNT_NAME_KEY;

public class ShouYeFragment extends BaseFragment {
    Banner<MNewsBean.ResultBean.DataBean, MBannerAdapter> banner;
    MBannerAdapter mBannerAdapter;
    ArrayList<MNewsBean.ResultBean.DataBean> dataBeans = new ArrayList<>();
    FixedIndicatorView indicator;
    ViewPager vpProperty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shouye, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        getData();

    }

    private void getData() {
        String tag = getArguments().getString(FRAGEMNT_NAME_KEY);
        HttpHelper.create(NewsApi.class)
                .getGuoNeiNewsList(tag, StaticConfig.NEWS_KEY, 1, 4, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MNewsBean.ResultBean>() {
                    @Override
                    public void onHandlerSuccess(MNewsBean.ResultBean value) {
                        if (value != null && !value.getData().isEmpty()) {
                            dataBeans.clear();
                            dataBeans.addAll(value.getData());
                            mBannerAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initView() {


        indicator = getView().findViewById(R.id.indicator);
        vpProperty = getView().findViewById(R.id.vpProperty);

        IndiactorViewPagerAdapter indiactorViewPagerAdapter = new IndiactorViewPagerAdapter(getChildFragmentManager());
        IndicatorViewPager indicatorViewPager = new IndicatorViewPager(indicator, vpProperty);
        indicatorViewPager.setAdapter(indiactorViewPagerAdapter);
        //indicator tab的设置
        indicator.setScrollBar(new ColorBar(getContext(), getContext().getResources().getColor(R.color.home_tab_sel_color),
                4, ScrollBar.Gravity.BOTTOM));
        //未选中时字体的大小
        float unSelectSize = 16f;
        //选中时字体的大小
        float selectSize = unSelectSize * 1.2f;
        //选中时字体的颜色
        int selectColor = getContext().getResources().getColor(R.color.ceruleanThree);
        //未选中时字体的颜色
        int unSelectColor = getContext().getResources().getColor(R.color.textPrimary);
        //监听事件
        OnTransitionTextListener textListener=new OnTransitionTextListener();
        textListener.setColor(selectColor, unSelectColor);
        textListener.setSize(selectSize, unSelectSize);
        indicator.setOnTransitionListener(textListener);


        banner = getView().findViewById(R.id.banner);
        mBannerAdapter = new MBannerAdapter(dataBeans);
        banner.setAdapter(mBannerAdapter);
        mBannerAdapter.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                MNewsBean.ResultBean.DataBean dataBean = (MNewsBean.ResultBean.DataBean) data;
                Intent intent = new Intent(getContext(), NewsDetailsActivity.class);
                intent.putExtra(NewsDetailsActivity.UNIQ_KEY, dataBean.getUniquekey());
                startActivity(intent);
            }
        });


    }


}
