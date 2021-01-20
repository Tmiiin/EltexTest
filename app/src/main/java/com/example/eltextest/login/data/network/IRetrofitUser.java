package com.example.eltextest.login.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class IRetrofitUser {

    private static final String BASE_URL = "http://smart.eltex-co.ru:8271/api/v1/";

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final UserAPI service = retrofit.create(UserAPI.class);

    public UserAPI getRetrofitService() {
        return this.service;
    }
}
