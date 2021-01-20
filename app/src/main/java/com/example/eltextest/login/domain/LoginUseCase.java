package com.example.eltextest.login.domain;

import com.example.eltextest.login.data.model.LoginResponse;

import io.reactivex.Single;
import retrofit2.Response;

public class LoginUseCase {

    private UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<Response<LoginResponse>> invoke(String login, String password) {
        return userRepository.loginUser(login, password);
    }
}
