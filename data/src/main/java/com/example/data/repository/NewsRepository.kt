package com.example.data.repository

import com.example.core.api.home.HomeAPIInterface
import com.example.core.remote.NetworkResult
import com.example.data.remote.NewsRemoteService
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val remoteDataSource: NewsRemoteService) {
    suspend fun getNews(search: String) = flow {
        emit(NetworkResult.Loading(true))
        val apiKey = "5269278c40fb42dc8924bee7c0483a56"
        val response = remoteDataSource.getNewsResult(search, apiKey)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}