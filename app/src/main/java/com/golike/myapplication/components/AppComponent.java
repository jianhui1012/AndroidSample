package com.golike.myapplication.components;

import android.content.Context;

import com.golike.myapplication.modules.AppModule;

import dagger.Component;

/**
 * Created by admin on 2017/8/3.
 */
@Component(modules = AppModule.class)
public interface AppComponent {
 Context getContext();
}
