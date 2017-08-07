package com.golike.myapplication.components;

import com.golike.myapplication.modules.MinePresenterModule;
import com.golike.myapplication.ui.activity.MainActivity;
import com.golike.myapplication.ui.fragment.MineFragment;

import dagger.Component;

/**
 * Created by admin on 2017/8/3.
 */
@Component(dependencies = AppComponent.class,modules = MinePresenterModule.class)
public interface MainComponent {
    MineFragment inject(MineFragment mineFragment);

    MainActivity inject(MainActivity mainActivity);
}
