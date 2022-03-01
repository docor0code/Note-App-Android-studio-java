package com.draco.noteapp.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {

    private static Retrofit instance;

    public RetrofitConnection() {
    }

    public static Retrofit getInstance() {

        if (instance == null){

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request oldRequest = chain.request();

                    Request.Builder newRequestBuilder = oldRequest.newBuilder();

                    newRequestBuilder.addHeader("accept", "application/json");
                    newRequestBuilder.method(oldRequest.method(), oldRequest.body());
                    return chain.proceed(newRequestBuilder.build());
                }
            }).build();
            instance = new Retrofit.Builder()
                    .baseUrl(ROUTES.MASTER_ROUT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return instance;
    }
}
