package com.golike.lookgank;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018/2/2.
 */

public class HttpUtils {

    public static final String BASE_URL = "http://gank.io/api/";

    private static final int DEFAULT_TIMEOUT = 3;

    private Retrofit retrofit;

    private ApiService apiService;

    private HttpUtils(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(builder.build()).addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(BASE_URL).build();

        apiService = retrofit.create(ApiService.class);
    }

    private static class SingleHolder{
        private static final HttpUtils httpUtils = new HttpUtils();
    }

    public static HttpUtils getInstance(){
        return SingleHolder.httpUtils;
    }

    public void getHomeData(Subscriber<SearchData> subscriber,@NonNull String type,@NonNull int count,@NonNull int page){
        apiService.getHomeData(type,count,page).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }
}
