package com.example.pgtestnews.domain.repository

import com.example.pgtestnews.domain.data.Article
import com.example.pgtestnews.errorHandle.Resource

interface Repository {

    suspend fun getTopHeadline(
        category : String
    ):Resource<List<Article>>

    suspend fun getSearchNews(
        query : String
    ):Resource<List<Article>>

}