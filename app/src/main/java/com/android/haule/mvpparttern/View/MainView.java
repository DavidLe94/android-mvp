package com.android.haule.mvpparttern.View;

public interface MainView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    void showMessage();

    void dataResponse(String res);
}
