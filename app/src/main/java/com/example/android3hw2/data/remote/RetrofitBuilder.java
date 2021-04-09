package com.example.android3hw2.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public RetrofitBuilder() {

    }

    private static GhibliApi instance;

    public static GhibliApi getInstance(){
        if (instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl("https://ghibliapi.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GhibliApi.class);
        }
        return  instance;
    }
}
