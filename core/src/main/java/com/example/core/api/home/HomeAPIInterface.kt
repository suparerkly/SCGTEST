package com.example.core.api.home

import com.example.core.model.home.News
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeAPIInterface {

    @GET("everything")
    suspend fun getNewsResults(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): News

}