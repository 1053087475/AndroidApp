package com.gpjypt.dome1.api;

import com.gpjypt.dome1.bean.MNewsBean;
import com.gpjypt.dome1.bean.NewsDetailsBean;
import com.gpjypt.dome1.http.BaseObserver;
import com.gpjypt.dome1.http.HttpRootResult;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("index")
    Observable<HttpRootResult<MNewsBean.ResultBean>> getGuoNeiNewsList(
            @Query("type") String type,
            @Query("key") String key,
            @Query("page") int page,
            @Query("page_size") int page_size,
            @Query("is_filter") boolean is_filter
    );

    @GET("content")
    Observable<HttpRootResult<NewsDetailsBean>> getNewsDetails(@Query("uniquekey") String uniquekey,
                                                               @Query("key") String key);
}
