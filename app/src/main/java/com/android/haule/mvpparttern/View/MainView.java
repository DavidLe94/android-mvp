package com.android.haule.mvpparttern.View;

public interface MainView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void loginSuccess();

    void loginError();

    void dataResponse(String res);
}
