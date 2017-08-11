package com.golike.myapplication.manager;

/**
 * Created by admin on 2017/8/10.
 */
public abstract class IAudioState {
    public IAudioState() {
    }

    void enter() {
    }

    abstract void handleMessage(AudioStateMessage var1);
}