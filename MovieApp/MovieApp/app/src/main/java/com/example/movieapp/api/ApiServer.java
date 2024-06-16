package com.example.movieapp.api;


import com.example.movieapp.api.ApiResponse.User.ChangeInfoRequest;
import com.example.movieapp.api.ApiResponse.User.ForgetResponse;
import com.example.movieapp.api.ApiResponse.User.ForgotRequest;
import com.example.movieapp.api.ApiResponse.User.ResetForgotPassRequest;
import com.example.movieapp.api.ApiResponse.User.ResetForgotPassResponse;
import com.example.movieapp.api.ApiResponse.User.ResetPassResponse;
import com.example.movieapp.api.ApiResponse.User.ResetPassResquest;
import com.example.movieapp.api.ApiResponse.User.UserResponse;
import com.example.movieapp.api.ApiResponse.login.LoginRequest;
import com.example.movieapp.api.ApiResponse.login.LoginResponse;
import com.example.movieapp.api.ApiResponse.registered.RegisResponse;
import com.example.movieapp.api.ApiResponse.registered.RegisterRes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServer {
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiServer apiServer = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServer.class);


    @POST ("api/login") // Đường dẫn API đăng nhập
     Call<LoginResponse> login(@Body LoginRequest accountRes);


@POST ("api/registered") // Đường dẫn API đăng nhập
Call<RegisResponse> register(@Body RegisterRes registerRes

);

    @GET("api/{id}")
    Call<UserResponse> getUser(@Path("id") String accountId,
                               @Header("Authorization") String authorization);
    @POST ("api/forgotpass") // Đường dẫn API đăng nhập
    Call<ForgetResponse> forgotPass(@Body ForgotRequest forgotRequest

    );


    //resetPass
    @POST("api/resetPass")
    Call<ResetPassResponse> resetPass(@Body ResetPassResquest resetPassRequest);

    @POST("api/changeInfo")
    Call<ResetPassResponse> changeInfo(@Body ChangeInfoRequest changeInfoRequest);

    @POST("api/resetForgotPass")
    Call<ResetForgotPassResponse> resetForgotPass(@Body ResetForgotPassRequest resetPassForgotRequest);
    /// gui email can email voi curent token

}
