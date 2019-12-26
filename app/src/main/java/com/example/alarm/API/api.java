package com.example.alarm.API;

import com.example.alarm.getdata.data;
import com.example.alarm.login.login;
import com.example.alarm.sendobj.sendobj;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface api {
    @FormUrlEncoded
    @POST("login")
    Call<login> loginsystem(
            @Field("email") String email,
            @Field("password") String pass
    );

    @Headers("Content-Type: application/json")
    @POST("data")
    Call<data> getdata(
            @Query("pagination") String page,
            @Query("order_by") String gsmdate,
            @Query("sort_by") String desc,
            @Query("token") String token,
            @Body String body
            );

}
