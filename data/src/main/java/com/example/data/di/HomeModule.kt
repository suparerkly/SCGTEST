package com.example.data.di

import com.example.data.remote.NewsRemoteService
import com.example.data.repository.NewsRepository
import org.koin.dsl.module

val homeModule = module {
    factory { NewsRepository(get()) }
    factory { NewsRemoteService(get()) }
}