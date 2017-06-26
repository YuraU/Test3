package com.yura.test3.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.yura.test3.api.API;
import com.yura.test3.api.ApiFactory;
import com.yura.test3.api.ApiService;
import com.yura.test3.mvp.model.response.WeatherResponse;
import com.yura.test3.mvp.view.AuthorizationView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AuthorizationPresenter extends BasePresenter<AuthorizationView>{

    public void getWeather(){
        final ApiService service = ApiFactory.createService(ApiService.class);

        final Observable<WeatherResponse> observable = service.getWeather("Moscow,ru", "metric", "ru", API.APPID);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull WeatherResponse numbersResponse) {
                        getViewState().showWeather(numbersResponse.main.get("temp").toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getViewState().showError();
                    }

                    @Override
                    public void onComplete() {}
                });
    }

}
