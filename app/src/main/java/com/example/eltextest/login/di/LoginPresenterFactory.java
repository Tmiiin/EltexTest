package com.example.eltextest.login.di;

import com.example.eltextest.login.domain.GetUserInfoUseCase;
import com.example.eltextest.login.data.datasource.UserDataSource;
import com.example.eltextest.login.data.datasource.UserDataSourceImpl;
import com.example.eltextest.login.data.repository.UserRepositoryImpl;
import com.example.eltextest.login.domain.LoginUseCase;
import com.example.eltextest.login.presentation.LoginPresenter;

public class LoginPresenterFactory {

    private static LoginPresenterFactory instance;

    private LoginPresenterFactory() {
    }

    public static LoginPresenterFactory getInstance() {
        if (instance == null) {
            instance = new LoginPresenterFactory();
        }
        return instance;
    }

    public LoginPresenter create() {
        UserDataSource userDataSource = new UserDataSourceImpl();
        UserRepositoryImpl userRepository = new UserRepositoryImpl(userDataSource);
        LoginUseCase loginUseCase = new LoginUseCase(userRepository);
        GetUserInfoUseCase getInfoUseCase = new GetUserInfoUseCase(userRepository);
        return new LoginPresenter(loginUseCase, getInfoUseCase);
    }
}
