package com.example.valute;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static  final String URL = "https://www.cbr-xml-daily.ru/";

    public static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Link getApi(){
        return getRetrofit().create(Link.class);
    }
}
