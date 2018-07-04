package com.android.haule.mvpparttern.Model;

import android.content.Context;

import com.android.haule.mvpparttern.API.ApiCallback;
import com.android.haule.mvpparttern.API.ApiManager;
import com.android.haule.mvpparttern.Presenter.MainPresenter;
import com.android.haule.mvpparttern.View.MainView;

public class MainPresenterImpl implements MainPresenter {
    private MainView loginView;

    public MainPresenterImpl(MainView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void validateCredentials(String email, String password) {
        loginView.showProgress();
        if(!email.isEmpty() && !password.isEmpty()){
            if(email.equals("Hau") && password.equals("123")){
                loginView.loginSuccess();
                loginView.hideProgress();
            }else{
                loginView.loginError();
                loginView.hideProgress();
            }
        }else{
            loginView.hideProgress();
            loginView.setPasswordError();
            loginView.setUsernameError();
        }
    }

    @Override
    public void callApi(Context context) {
        ApiManager.getInstance(context).getData(new ApiCallback() {
            @Override
            public void ApiResponse(String res) {
                loginView.dataResponse(res);
            }
        });
    }
}
