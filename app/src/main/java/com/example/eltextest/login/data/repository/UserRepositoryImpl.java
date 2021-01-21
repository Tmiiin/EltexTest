package com.example.eltextest.login.data.repository;

import com.example.eltextest.login.data.datasource.UserDataSource;
import com.example.eltextest.login.data.model.LoginResponse;
import com.example.eltextest.login.data.model.UserInfoResponse;
import com.example.eltextest.login.domain.UserRepository;


import io.reactivex.Single;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {

    private static UserDataSource userDataSource;

    public UserRepositoryImpl(UserDataSource dataSource) {
        userDataSource = dataSource;
    }

    @Override
    public Single<Response<LoginResponse>> loginUser(String login, String password) {
        return userDataSource.login(login, password);
    }

    @Override
    public Single<Response<UserInfoResponse>> getUserInfo(String token){
        return userDataSource.getUserInfo(token);
    }

}
