package com.gpjypt.dome1.test;

import com.gpjypt.dome1.http.HttpRootResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MApi {
    @GET("index")
    Observable<HttpRootResult<Object>> getNewsIndex(@Query("type")String type,
                                                    @Query("key")String key,
                                                    @Query("page")int page );
}
