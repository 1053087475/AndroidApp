package com.gpjypt.dome1.http;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtils {
    public static void loadImgWithUrl(Context context, String imgUrl, ImageView iv) {
        if (imgUrl == null || TextUtils.isEmpty(imgUrl)) return;
        RequestOptions requesOptions = new RequestOptions()
                .dontAnimate()
                .format(DecodeFormat.PREFER_ARGB_8888);
        Glide.with(context)
                .load(imgUrl)
                .apply(requesOptions)
                .into(iv);

    }
}
