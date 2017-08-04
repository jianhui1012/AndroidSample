package com.golike.myapplication.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017/8/3.
 */
@Module
public class AppModule {

    private  Context mContext;

    public AppModule(Context context){
        this.mContext=context;
    }

    @Provides
    public Context provideContext(){
        return this.mContext;
    }


}
