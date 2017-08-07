package com.golike.myapplication;

import android.app.Application;

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
    }

    public static SampleApplication  getInstance(){
        return mInstance;
    }

    public AppComponent getAppComponent(){
        return  appComponent;
    }

}
