package com.example.eltextest.login.data.network;

import com.example.eltextest.login.data.model.LoginResponse;
import com.example.eltextest.login.data.model.UserInfoResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {

    @GET("user")
    Single<Response<UserInfoResponse>> getUserInfo(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("oauth/token")
    Single<Response<LoginResponse>> login(@Header("Authorization") String token,
                                         @Field("username") String username,
                                         @Field("password") String password,
                                         @Field("grant_type") String grantType);

}
