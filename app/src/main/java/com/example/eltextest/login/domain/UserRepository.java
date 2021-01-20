package com.example.eltextest.login.domain;

import com.example.eltextest.login.data.model.LoginResponse;
import com.example.eltextest.login.data.model.UserInfoResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public interface UserRepository {

     Single<Response<LoginResponse>> loginUser(String login, String password);

     Single<Response<UserInfoResponse>> getUserInfo(String token);
}
