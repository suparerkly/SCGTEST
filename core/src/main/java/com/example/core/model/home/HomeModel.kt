package com.example.core.model.home

data class News(
    val status: String? = "",
    val totalResults: Long? = 0L,
    val articles: List<Article>? = null,
)

data class Article(
    val source: Source? = null,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = "",
)

data class Source(
    val id: String? = "",
    val name: String? = "",
)