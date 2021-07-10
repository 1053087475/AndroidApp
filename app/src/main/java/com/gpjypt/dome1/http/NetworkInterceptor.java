package com.gpjypt.dome1.http;

import java.io.IOException;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkInterceptor implements Interceptor {
    private Class tag = NetworkInterceptor.class;

    @Override
    public Response intercept(Chain chain) throws IOException {
        //缓存，如果忽略服务器处理，自己定义
        String cacheControl = chain.request().cacheControl().toString();
        Request request = chain.request().newBuilder()
                .addHeader("Connection", "close")
                .removeHeader("Accept-Encoding").build();
        Response response = chain.proceed(request);
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();

        //输出请求头
        if (request.headers().size() != 0) {
            String[] sp = chain.request().headers().toString().split("\n");
            StringBuilder sb = new StringBuilder();
            for (int index = 0; index < sp.length; index++) {
                if (!sp[index].isEmpty()) {
                    sb.append(sp[index]);
                }
            }
            LogUtils.logV(tag, "\t|" + sb.toString());
        }


        //输出响应头
        if (response.headers().size() != 0) {
            Date date = response.headers().getDate("Date");
            //输出响应头
            if (response.headers().size() != 0) {
                String[] sp = response.headers().toString().split("\n");
                StringBuilder sb = new StringBuilder();
                for (int index = 0; index < sp.length; index++) {
                    if (!sp[index].isEmpty()) {
                        sb.append(sp[index]);
                    }
                }
                LogUtils.logV(tag, "\t|" + sb.toString());
            }
        }


        return response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", cacheControl)
                .body(ResponseBody.create(mediaType, content)).build();
    }
}
