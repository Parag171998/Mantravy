package com.example.mantravy.Network;

import com.example.mantravy.Models.CurrentCity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("user-key: 1d63821dbb228ee5d09e8c8f7cfe10c3")
    @GET("/api/v2.1/geocode")
    Call<CurrentCity> getRestaurant(@Query("lat") Double lat, @Query("lon") Double lon );


}
