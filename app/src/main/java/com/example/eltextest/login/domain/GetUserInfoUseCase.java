package com.example.eltextest.login.domain;

import com.example.eltextest.login.data.model.UserInfoResponse;

import io.reactivex.Single;
import retrofit2.Response;

public class GetUserInfoUseCase {

    private final UserRepository userRepository;

    public GetUserInfoUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<Response<UserInfoResponse>> invoke(String token) {
        return userRepository.getUserInfo(token);
    }

}
