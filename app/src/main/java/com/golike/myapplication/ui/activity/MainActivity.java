package com.golike.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.golike.myapplication.R;
import com.golike.myapplication.SampleApplication;
import com.golike.myapplication.components.DaggerMainComponent;
import com.golike.myapplication.modules.MinePresenterModule;
import com.golike.myapplication.presenter.MinePresenter;
import com.golike.myapplication.ui.fragment.DynamicFragment;
import com.golike.myapplication.ui.fragment.HomeFragment;
import com.golike.myapplication.ui.fragment.MineFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    private FragmentManager mFragMgr;

    @Inject
    MinePresenter presenter;

    @BindView(R.id.simplecomponents)
    ImageButton simplecomponents;
    @BindView(R.id.framework)
    ImageButton framework;
    @BindView(R.id.lab)
    ImageButton lab;

    private MineFragment mineFragment;
    private HomeFragment homeFragment;
    private DynamicFragment dynamicFragment;

    private final String MINE = "Mine";
    private final String HOME = "Home";
    private final String DYNAMIC="Dynamic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragMgr = getSupportFragmentManager();
        initFragments();
        showFragments(HOME, true);
    }

    private void initFragments() {
        mineFragment = new MineFragment();
        homeFragment = new HomeFragment();
        dynamicFragment=new DynamicFragment();
        DaggerMainComponent.builder().minePresenterModule(new MinePresenterModule(mineFragment)).appComponent(SampleApplication.getInstance().getAppComponent()).build().inject(this);
    }

    private void showFragments(String tag, boolean init) {
        FragmentTransaction trans = mFragMgr.beginTransaction();
        if (init) {
            trans.add(R.id.main_content, getFragmentByTag(tag), tag);
            //trans.addToBackStack(tag);
        } else {
            trans.replace(R.id.main_content, getFragmentByTag(tag), tag);
        }
        trans.commit();
    }

    private Fragment getFragmentByTag(String tag) {
        if (MINE.equals(tag)) {
            return mineFragment;
        } else if (HOME.equals(tag)) {
            return homeFragment;
        }else if(DYNAMIC.equals(tag)){
            return  dynamicFragment;
        }

        return null;
    }

    @OnClick({R.id.simplecomponents, R.id.framework, R.id.lab})
    public void chooseTab(View view) {
        switch (view.getId()) {
            case R.id.simplecomponents:
                showFragments(HOME, false);
                break;
            case R.id.framework:
                showFragments(MINE, false);
                break;
            case R.id.lab:
                showFragments(DYNAMIC,false);
                break;
        }
    }


}
