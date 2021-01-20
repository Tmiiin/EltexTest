package com.example.eltextest.login.data.datasource;

import com.example.eltextest.login.data.model.LoginResponse;
import com.example.eltextest.login.data.model.UserInfoResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public interface UserDataSource {

    Single<Response<LoginResponse>> login(String username,
                                          String password);

    Single<Response<UserInfoResponse>> getUserInfo(String token);
}
