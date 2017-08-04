package com.golike.myapplication.presenter;

import android.os.Handler;
import android.util.Log;
import com.golike.myapplication.model.User;

import javax.inject.Inject;

/**
 * Created by admin on 2017/8/2.
 */

public class MinePresenter implements MineContract.Presenter {

    private final static String TAG="MinePresenter";

    private MineContract.View mView;

    @Inject
    public MinePresenter(MineContract.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void login(User user) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.login_success("success");
                Log.d(TAG,"logined");
            }
        },500);

    }
}
