package com.gpjypt.dome1.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.gpjypt.dome1.R;
import com.gpjypt.dome1.fragment.ShouYeChildFragment;
import com.gpjypt.dome1.utils.StaticConfig;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;

public class IndiactorViewPagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    ArrayList<String> tabs = new ArrayList<>();

    public IndiactorViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        tabs.add(StaticConfig.tag_sy_caijing);
        tabs.add(StaticConfig.tag_sy_keji);
        tabs.add(StaticConfig.tag_sy_shishang);
        tabs.add(StaticConfig.tag_sy_youxi);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.tab_item, container, false);
        TextView tvTabTitle = view.findViewById(R.id.tvTabTitle);
        tvTabTitle.setText(getTabName(tabs.get(position)));
        return view;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        ShouYeChildFragment childFragment = new ShouYeChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ShouYeChildFragment.TAB_NAME_KEY, tabs.get(position));
        childFragment.setArguments(bundle);
        return childFragment;
    }

    private String getTabName(String tag) {
        if (tag.equals(StaticConfig.tag_sy_caijing)) {
            return "财经";
        } else if (tag.equals(StaticConfig.tag_sy_keji)) {
            return "科技";
        } else if (tag.equals(StaticConfig.tag_sy_shishang)) {
            return "时尚";
        } else if (tag.equals(StaticConfig.tag_sy_youxi)) {
            return "游戏";
        } else return "财经";
    }
}
