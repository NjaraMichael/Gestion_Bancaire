/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GPB.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 *
 * @author Mendrikaja
 */
public class UserAPI {
    private static Retrofit retrofit = null;
    
    public static Retrofit getUser() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient user = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(user)
                .build();
        
        return retrofit;
    }
}
