package com.yogeshlokhande.reposassignment.api

import com.yogeshlokhande.reposassignment.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {


    @GET(NEWS_API)
    suspend fun fetchAllNews(
        @Query("country") COUNTRY_QUERY: String,
        @Query("apiKey") APIKEY_QUERY: String
    ): NewsResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val NEWS_API = "v2/top-headlines"
    }
}