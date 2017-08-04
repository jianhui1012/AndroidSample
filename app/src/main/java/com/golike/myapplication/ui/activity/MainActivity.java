package com.golike.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.golike.myapplication.R;
import com.golike.myapplication.SampleApplication;
import com.golike.myapplication.components.DaggerMainComponent;
import com.golike.myapplication.presenter.MinePresenter;
import com.golike.myapplication.ui.fragment.DynamicFragment;
import com.golike.myapplication.ui.fragment.HomeFragment;
import com.golike.myapplication.ui.fragment.MineFragment;

import javax.inject.Inject;

public class MainActivity extends FragmentActivity  {

    private FragmentManager mFragMgr;

    @Inject
     MinePresenter presenter;

    private MineFragment mineFragment;
    private DynamicFragment dynamicFragment;
    private HomeFragment homeFragment;

    private final String MINE = "Mine";
    private final String DYNAMIC = "dynamic";
    private final String HOME = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragMgr = getSupportFragmentManager();
        initFragments();
        showFragments(MINE,true);
    }

    private void initFragments() {
        mineFragment = new MineFragment();
        presenter = new MinePresenter(mineFragment);
        dynamicFragment = new DynamicFragment();
        homeFragment = new HomeFragment();
    }

    private void showFragments(String tag, boolean init) {
        FragmentTransaction trans = mFragMgr.beginTransaction();
        if (init) {
            trans.add(R.id.main_content, getFragmentByTag(tag), tag);
            trans.addToBackStack(tag);
        } else {
            trans.replace(R.id.main_content, getFragmentByTag(tag), tag);
        }
        trans.commit();
    }

    private Fragment getFragmentByTag(String tag) {
        if (MINE.equals(tag)) {
            return mineFragment;
        }
        if (DYNAMIC.equals(tag)) {
            return dynamicFragment;
        }
        if (HOME.equals(tag)) {
            return homeFragment;
        }
        return null;
    }


}
