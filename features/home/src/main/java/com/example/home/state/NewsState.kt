package com.example.home.state

import com.example.core.model.home.Article
import com.example.core.model.home.News

data class NewsUIState(
    val data: News? = null,
    val message: String? = null
)

data class NewsDetailState(
    val data: Article? = null,
    val message: String? = null
)
