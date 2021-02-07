package com.example.newsfeeds;

import com.example.newsfeeds.NewsClasses.Headline;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
        Call<Headline> getHeadline(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
}
