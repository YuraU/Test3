package com.yura.test3.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface AuthorizationView extends MvpView {

    void showWeather(String weather);

    void showError();
}
