package com.android.haule.mvpparttern.Presenter;

import android.content.Context;

public interface MainPresenter {
    void validateCredentials(String email, String password);

    void callApi(Context context);
}