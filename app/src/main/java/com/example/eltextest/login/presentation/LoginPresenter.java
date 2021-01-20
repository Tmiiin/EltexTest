package com.example.eltextest.login.presentation;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.util.Log;

import com.example.eltextest.R;
import com.example.eltextest.login.data.model.UserInfoResponse;
import com.example.eltextest.login.domain.GetUserInfoUseCase;
import com.example.eltextest.login.data.model.LoginResponse;
import com.example.eltextest.login.domain.LoginUseCase;
import com.example.eltextest.login.ui.LoginFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginPresenter {

    private final LoginUseCase loginUseCase;
    private final GetUserInfoUseCase getInfoUseCase;
    private LoginFragment view = null;
    private final String TAG = "Login";
    private final String token = "token";


    public LoginPresenter(LoginUseCase loginUseCase, GetUserInfoUseCase getInfoUseCase) {
        this.loginUseCase = loginUseCase;
        this.getInfoUseCase = getInfoUseCase;
    }

    public void attachView(LoginFragment view) {
        this.view = view;
    }

    public boolean isValid(String parameter) { // валидным параметр будет считаться если его длина > 3
        // т.к. не сказано иное
        return parameter.length() > 3;
    }

    public void tryLogin(String login, String password) {
        if (isValid(login) && isValid(password)) {
            loginUseCase.invoke(login, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new DisposableSingleObserver<Response<LoginResponse>>() {
                        @Override
                        public void onSuccess(@NonNull Response<LoginResponse> loginResponse) {
                            switch (loginResponse.code()) {
                                case 500: {
                                    view.makeErrorToast("500 ошибка!!!!!");
                                    break;
                                }
                                case 200: {
                                    handleLoginResponse(loginResponse.body().getAccess_token(), login, password);
                                    break;
                                }
                                case 400: {
                                    view.makeErrorToast("Что-то нет такого аккаунта на сервере....");
                                    break;
                                }
                                default:
                                    Log.e(TAG, String.valueOf(loginResponse.code()));
                            }
                            view.hideChildProgressBar();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "throw exception " + e.getMessage());
                            view.hideChildProgressBar();
                            view.makeErrorToast(e.getMessage());
                        }
                    });
        } else {
            view.hideChildProgressBar();
            view.makeErrorToast("Параметры не валидны");
        }
    }

    private void handleLoginResponse(String accessToken, String login, String password) {
        Log.e(TAG, accessToken);
        createOrUpdateAccount(login, password, accessToken);
        checkToken(accessToken);
    }

    public void checkNeedLogin() {
        Account myAccount = getAccount();
        if (myAccount != null) { //если на устройстве уже есть зарегистрированный аккаунт
            //проверяем, валидный ли токен сохранен
            checkToken(AccountManager.get(view.getContext()).getUserData(myAccount, token));
        } else {
            view.showEnterScreen();
        }
    }

    public void checkToken(String token) {
        getInfoUseCase.invoke(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<Response<UserInfoResponse>>() {
                    @Override
                    public void onSuccess(@NonNull Response<UserInfoResponse> userResponse) {
                        switch (userResponse.code()) {
                            case 500: {
                                view.makeErrorToast("500 ошибка в проверке токена!!!!!");
                                view.showEnterScreen();
                                break;
                            }
                            case 200: {
                                view.goToInfoView(userResponse.body());
                                break;
                            }
                            case 401: {
                                view.makeErrorToast("чото токен не тот");
                                view.showEnterScreen();
                                break;
                            }
                        }
                        view.hideChildProgressBar();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "throw exception " + e.getMessage());
                        view.hideChildProgressBar();
                        view.makeErrorToast(e.getMessage());
                    }
                });
    }

    public void onDestroy() {
        this.view = null;
    }

    private void createOrUpdateAccount(String login, String password, String token) {
        AccountManager accountManager = AccountManager.get(view.getContext());
        Bundle options = new Bundle();
        Account myAccount;
        for (Account account : accountManager.getAccounts()) {
            if (account.name.equals(login)) { //если такой аккаунт был зарегистрирован ранее
                accountManager.setPassword(account, password);//меняем его данные
                options.putString(this.token, token);
                accountManager.setUserData(account, this.token, token);
                return;
            }
        }
        myAccount = new Account(login,
                Objects.requireNonNull(view.getContext()).getString(R.string.domain));
        options.putString(this.token, token);
        accountManager.addAccountExplicitly(myAccount, password, options);
        Log.e(TAG, accountManager.getUserData(myAccount, this.token) + " - token is");
    }

    private Account getAccount() {
        AccountManager accountManager = AccountManager.get(view.getContext());
        Account[] accounts = accountManager
                .getAccountsByType(Objects.requireNonNull(view.getContext()).getString(R.string.domain));
        Account myAccount;
        Log.e(TAG, Arrays.toString(accountManager
                .getAccounts()));
        if (accounts.length == 1) { //если на устройстве зарегистрирован был только один пользователь
            myAccount = accounts[0];
            return myAccount;
        } else ;//если много - дать пользователю выбор (не реализовано, т.к. пришлось бы делать
        // диалоговое окно с выбором конкретного аккаунта)
        return null;
    }

}
