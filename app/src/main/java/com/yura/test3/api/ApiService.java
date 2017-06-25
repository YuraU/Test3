package com.yura.test3.api;

import com.yura.test3.api.response.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yura on 13.12.16.
 */

public interface ApiService {

    @GET(API.URL_WEATHER)
    Observable<WeatherResponse> getWeather(@Query("q") String q, @Query("units") String units, @Query("lang") String lang, @Query("APPID") String appid);
}
