package com.golike.myapplication.manager;

/**
 * Created by admin on 2017/8/10.
 */

public class AudioStateMessage {
    public int what;
    public Object obj;

    public AudioStateMessage() {
    }

    public static AudioStateMessage obtain() {
        return new AudioStateMessage();
    }
}
