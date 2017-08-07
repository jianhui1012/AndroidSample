package com.golike.myapplication.modules;

import com.golike.myapplication.presenter.MineContract;

import dagger.Module;
import dagger.Provides;


@Module
public class MinePresenterModule {

    private final MineContract.View mView;

    public MinePresenterModule(MineContract.View view) {
        mView = view;
    }

    @Provides
    MineContract.View provideStatisticsContractView() {
        return mView;
    }
}
