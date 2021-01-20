package com.example.eltextest.login.domain;

import com.example.eltextest.login.data.model.UserInfoResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public class GetUserInfoUseCase {

    private UserRepository userRepository;

    public GetUserInfoUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<Response<UserInfoResponse>> invoke(String token) {
        return userRepository.getUserInfo(token);
    }

}
