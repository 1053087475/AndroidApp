package com.gpjypt.dome1.http;


import com.gpjypt.dome1.utils.StaticConfig;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<HttpRootResult<T>> {
    public BaseObserver(){
        super();
    }
    @Override
    public void onSubscribe(Disposable d) {

    }
    public abstract void onHandlerSuccess(T value);

    @Override
    public void onNext(HttpRootResult<T> tHttpRootResult) {
        if ("0".equals(tHttpRootResult.getError_code())){
            onSuccessNext(tHttpRootResult);
            onHandlerSuccess(tHttpRootResult.getResult());
        }else {
            onHandlerError(tHttpRootResult.getError_code(),tHttpRootResult.getReason());
        }
    }

    @Override
    public void onError(Throwable e) {

//        e.printStackTrace();

            if (!LoggingInterceptor.isNetworkAvailable()) {
                StaticConfig.toast("当前网络不可用，请检查网络情况");
            } else {

                if (e instanceof HttpException){
                    showErrorCode(e, "510");
                }else if (e instanceof SocketTimeoutException){
                    showErrorCode(e, "511");
                }else if (e instanceof UnknownHostException){
                    showErrorCode(e, "512");
                }else if (e instanceof ConnectException){
                    showErrorCode(e, "512");
                }else if (e instanceof SSLHandshakeException){
                    StaticConfig.toast("证书校验失败，请稍后重试");
                }else if (e instanceof JSONException){
                    StaticConfig.toast("JSONException-解析错误");
                    showErrorCode(e, "513");
                }else {

                    showErrorCode(e, "514");
//                    throw (){}
                }

            }
        LogUtils.logE("onError() called with: [" + e.toString() + "]");
    }
    @Override
    public void onComplete() {

    }
    public void onSuccessNext(HttpRootResult<T> value) {


    }
    public void onHandlerError(String code, String msg) {
        StaticConfig.toast("code=="+code+"\nmsg=="+msg);
    }
    private void showErrorCode(Throwable e, String code) {
        StaticConfig.toast("code="+code+"\ne="+e.getMessage());
//        CrashReport.testJavaCrash()
//        CrashReport.postCatchedException(e)
    }
}
