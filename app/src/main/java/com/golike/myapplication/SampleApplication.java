package com.golike.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.golike.customviews.ChatViewManger;
import com.golike.customviews.RongContext;
import com.golike.myapplication.components.AppComponent;
import com.golike.myapplication.components.DaggerAppComponent;
import com.golike.myapplication.modules.AppModule;

/**
 * Created by admin on 2017/7/24.
 */

public class SampleApplication extends Application {

    private AppComponent appComponent;

    private static SampleApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        appComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        ChatViewManger.init(this);
    }



    public static SampleApplication  getInstance(){
        return mInstance;
    }

    public AppComponent getAppComponent(){
        return  appComponent;
    }

}
