package com.example.pgtestnews.domain.api

import com.example.pgtestnews.domain.data.NewsModule
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    companion object{
        const val BASE_URL = " https://newsapi.org/v2/"
        const val API_KEY="81ca6a65ba0d41b28bb229a952c9cf28"
    }


    @GET("top-headlines")
    suspend fun getTopHeadline(
        @Query("country") country : String ="in",
        @Query("category") category : String,
        @Query("apikey") apiKey : String = API_KEY
    ):NewsModule

    @GET("everything")
    suspend fun getSearchNews(
        @Query("q") query : String,
        @Query("apikey") apiKey : String = API_KEY
    ):NewsModule

}