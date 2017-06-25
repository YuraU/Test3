package com.yura.test3.mvp.presenter;


import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    @NonNull
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
}
