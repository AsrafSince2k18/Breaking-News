package com.example.pgtestnews.domain.data

data class NewsModule(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)