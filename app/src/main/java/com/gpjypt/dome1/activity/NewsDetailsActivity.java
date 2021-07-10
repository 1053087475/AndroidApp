package com.gpjypt.dome1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gpjypt.dome1.R;
import com.gpjypt.dome1.api.NewsApi;
import com.gpjypt.dome1.base.BaseActivity;
import com.gpjypt.dome1.base.PubWebView;
import com.gpjypt.dome1.bean.NewsDetailsBean;
import com.gpjypt.dome1.http.BaseObserver;
import com.gpjypt.dome1.http.HttpHelper;
import com.gpjypt.dome1.utils.StaticConfig;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailsActivity extends BaseActivity {
    public static String UNIQ_KEY = "key_uniq";
    private TextView tvNewsTitle, tvAuther, tvDate,tvTitle;
    private ImageView ivPic,ivBack;
    private PubWebView pwvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initView();
        getData();
    }

    private void initView() {
        //新闻标题
        tvNewsTitle = findViewById(R.id.tvNewsTitle);
        //作者
        tvAuther = findViewById(R.id.tvAuther);
        //日期
        tvDate = findViewById(R.id.tvDate);
        //内容
        pwvContent = findViewById(R.id.pwvContent);
        //图片
        ivPic = findViewById(R.id.ivPic);

        tvTitle=findViewById(R.id.tvTitle);
        tvTitle.setText("新闻详情");

    }

    private void getData() {
        String uniq = getIntent().getStringExtra(UNIQ_KEY);
        HttpHelper.create(NewsApi.class)
                .getNewsDetails(uniq, StaticConfig.NEWS_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<NewsDetailsBean>() {
                    @Override
                    public void onHandlerSuccess(NewsDetailsBean value) {
                        if (value != null) {
                            tvNewsTitle.setText(value.getDetail().getTitle());
                            tvAuther.setText("作者：" + value.getDetail().getAuthor_name());
                            tvDate.setText(value.getDetail().getDate());

                            pwvContent.loadData(value.getContent(),"text/html","utf-8");
                            String picUrl = value.getDetail().getThumbnail_pic_s();
                            if (!TextUtils.isEmpty(picUrl)) {
                                Glide.with(ivPic).load(picUrl).into(ivPic);
                            }

                        }
                    }
                });
    }
}