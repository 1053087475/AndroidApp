package com.gpjypt.dome1.base;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PubWebView extends WebView {
    public PubWebView(@NonNull Context context) {
        super(context);
    }

    public PubWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initWebView() {
        clearCache(true);
        clearHistory();

        WebSettings settings = this.getSettings();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // AgentWeb 没有把WebView的功能全面覆盖 ，所以某些设置 AgentWeb 没有提供 ， 请从WebView方面入手设置。
        this.setOverScrollMode(WebView.OVER_SCROLL_NEVER);


        settings.setJavaScriptEnabled(true);
        //优先使用网络
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //将图片调整到适合webview的大小
        settings.setUseWideViewPort(true);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);

        settings.setLoadWithOverviewMode(true);
        //开启DOM storage API功能（HTML5 提供的一种标准的接口，主要将键值对存储在本地，在页面加载完毕后可以通过 javascript 来操作这些数据。）
        settings.setDomStorageEnabled(true);
        //支持缩放
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        //去掉滚动条
        this.setHorizontalScrollBarEnabled(false);
        this.setVerticalScrollBarEnabled(false);
        //不显示webview缩放按钮
        settings.setDisplayZoomControls(false);
        this.setBackgroundColor(0);

    }
}
