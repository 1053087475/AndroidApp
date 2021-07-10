package com.gpjypt.dome1.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gpjypt.dome1.App;

import java.io.IOException;
import java.util.Set;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {
    private String timesTamp = String.valueOf(System.currentTimeMillis());
    private Class tag = LoggingInterceptor.class;

    @Override
    public Response intercept(Chain chain) throws IOException {
        StringBuilder stringBuffer=new StringBuilder();
        for (int j=0;j<120;j++){
            stringBuffer.append("-");
        }
        LogUtils.logV(tag,"\t┌"+stringBuffer.toString());
        Request request;
        if (isNetworkAvailable()) {
            request = chain.request()
                    .newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        } else {
            LogUtils.logV(tag, "\t│ Loaded from LocalCache - Network error");
            request = chain.request().newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        LogUtils.logV(tag, "\t│ Request{method= " + request.method() + "url=" + request.url());


        String hs = "";
        Headers headers = request.headers();
        Set<String> names = headers.names();
        for (String name : names) {
            hs = hs + "," + name + "=" + headers.get(name);
        }
        LogUtils.logV(tag, "\t| Header:" + hs);


        if (request.body() != null)
            LogUtils.logV(tag, "\t| Body:" + request.body().toString());


        Long t1 = System.nanoTime();
        Response response = chain.proceed(request);
        Long t2=System.nanoTime();
        LogUtils.logV(tag,"\t| Received response for in "+(t2-t1)/1e6+"ms");

        MediaType mediaType= response.body().contentType();
        String content=response.body().string();
        if (!content.isEmpty()){
            LogUtils.logV(tag,"\t|"+content.replace("\n",""));
        }

        LogUtils.logV(tag,"\t└"+stringBuffer.toString());

        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType,content)).build();
    }

    /**
     * 判断网络是否连接
     * <p>
     * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
     *
     * @return `true`: 是<br></br>`false`: 否
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) App.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

}
