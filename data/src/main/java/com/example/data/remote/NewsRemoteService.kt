package com.example.data.remote

import com.example.core.api.home.HomeAPIInterface

class NewsRemoteService(
    private val apiService: HomeAPIInterface
) {
    suspend fun getNewsResult(search: String, apiKey: String) = apiService.getNewsResults(search, apiKey)
}