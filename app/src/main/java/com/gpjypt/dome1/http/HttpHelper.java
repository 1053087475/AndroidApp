package com.gpjypt.dome1.http;

import com.gpjypt.dome1.App;
import com.gpjypt.dome1.utils.StaticConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpHelper {
    private static Long DEFAULT_TIMEOUT = 60L;
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    private static void init() {
        mOkHttpClient= new OkHttpClient.Builder().retryOnConnectionFailure(true)
                .cache(new Cache(new File(App.mContext.getCacheDir(), "http_cache"),
                        +1024 * 1024 * 100))
                .addNetworkInterceptor(new NetworkInterceptor())
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LoggingInterceptor())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .build();

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(StaticConfig.BASE_URL)
                .client(mOkHttpClient)
                .build();
    }
    public static <T>  T create(Class<T> tClass){
        if (mOkHttpClient==null)init();
        return mRetrofit.create(tClass);
    }
}
