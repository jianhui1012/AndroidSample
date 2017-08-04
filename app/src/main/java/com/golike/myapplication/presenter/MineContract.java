package com.golike.myapplication.presenter;

import com.golike.myapplication.base.BasePresenter;
import com.golike.myapplication.base.BaseView;
import com.golike.myapplication.model.User;

/**
 * Created by admin on 2017/8/1.
 */

public interface MineContract {

    interface View extends BaseView<Presenter>{

        void login_success(String response);

        void login_failure(String msg);

        void network_exception(int errcode);

        void network_normal();

    }

    interface Presenter extends  BasePresenter{

        void login(User user);

    }

}
