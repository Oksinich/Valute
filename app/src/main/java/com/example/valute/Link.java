package com.example.valute;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface Link {

    @GET("daily_json.js")
    Call<DataLink> allData();
}
