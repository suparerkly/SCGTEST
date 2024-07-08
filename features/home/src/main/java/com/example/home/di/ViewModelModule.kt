package com.example.home.di

import com.example.home.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsViewModel(repository = get()) }
    viewModelOf(::NewsViewModel)
}