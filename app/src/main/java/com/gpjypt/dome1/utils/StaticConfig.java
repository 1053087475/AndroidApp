package com.gpjypt.dome1.utils;

import android.annotation.SuppressLint;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gpjypt.dome1.App;
import com.gpjypt.dome1.R;


public class StaticConfig {
    public static final String USER_HEADER = "user_";
    public static final String PWD_HEADER = "pwd_";
    public static int inputTypeHide = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
    public static int inputTypeShow = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    public static String IS_LOGIN_KEY = "is_login_key";
    public static String USER_NAME="user_name";


    public static String BASE_URL = "http://v.juhe.cn/toutiao/";
//
   public static String NEWS_KEY = "1bafa5d0aecb5d69bcf113d4e974a314";
//    public static String NEWS_KEY = "a1c58158360a28d5146b240341934e7e";


    public static String tag_sy="top";
    public static String tag_gn = "guonei";
    public static String tag_gj = "guoji";
    public static String tag_yl = "yule";
    public static String tag_ty = "tiyu";
    public static String tag_wd = "wode";

    public static String tag_sy_keji="keji";
    public static String tag_sy_caijing="caijing";
    public static String tag_sy_shishang="shishang";
    public static String tag_sy_youxi="youxi";

    @SuppressLint("WrongConstant")
    public static void toast(String msg) {
        Toast.makeText(App.mContext, msg, 2000).show();
    }

    public static void setPwdEditView(EditText etPwd, ImageView imageView) {
        etPwd.setInputType(inputTypeHide);
        imageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if (etPwd.getInputType() == inputTypeHide) {
                    etPwd.setInputType(inputTypeShow);
                    imageView.setImageDrawable(App.mContext.getDrawable(R.drawable.login_xianshi_mima));

                } else {
                    etPwd.setInputType(inputTypeHide);
                    imageView.setImageDrawable(App.mContext.getDrawable(R.drawable.login_yinccang_mima));
                }

            }
        });
    }
}
