package com.example.pgtestnews.domain.repository

import com.example.pgtestnews.domain.api.NewsApi
import com.example.pgtestnews.domain.data.Article
import com.example.pgtestnews.errorHandle.Resource

class RepositoryImpl (private val newsApi: NewsApi) : Repository {
    override suspend fun getTopHeadline(category: String): Resource<List<Article>> {
        return try{
            val response = newsApi.getTopHeadline(category = category)
            Resource.Success(data = response.articles)

        }catch (e : Exception){
            Resource.Error(messgage = e.message)
        }
    }

    override suspend fun getSearchNews(query: String): Resource<List<Article>> {
        return try{
            val response = newsApi.getSearchNews(query = query)
            Resource.Success(data = response.articles)

        }catch (e : Exception){
            Resource.Error(messgage = e.message)
        }
    }
}