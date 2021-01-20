package com.example.eltextest.login.data.datasource;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.eltextest.login.data.model.LoginResponse;
import com.example.eltextest.login.data.model.UserInfoResponse;
import com.example.eltextest.login.data.network.IRetrofitUser;
import com.example.eltextest.login.data.network.UserAPI;

import java.nio.charset.StandardCharsets;
import java.util.List;

import android.util.Base64;

import io.reactivex.Single;
import retrofit2.Response;

public class UserDataSourceImpl implements UserDataSource {

    static UserAPI service = (new IRetrofitUser()).getRetrofitService();

    public static final String grantType = "password";
    private static final String credentials = "android-client:password";
    private static String TAG = "UserDataSource";
    private static final String tokenType = "Basic";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Single<Response<LoginResponse>> login(String username, String password) {
        byte[] data = credentials.getBytes(StandardCharsets.UTF_8);
        String encodedCredentials = Base64.encodeToString(data, Base64.NO_WRAP);
        StringBuilder authorizationString = new StringBuilder()
                .append(tokenType).append(" ").append(encodedCredentials);
        Log.e(TAG, "Authorization string is: " + authorizationString.toString());
        return service.login(authorizationString.toString(), username, password, grantType);
    }

    public Single<Response<UserInfoResponse>> getUserInfo(String token) {
        return service.getUserInfo("Bearer " + token);
    }

}
