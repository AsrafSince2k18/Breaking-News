package com.example.pgtestnews.ui.presentance.screen

import com.example.pgtestnews.domain.data.Article

data class NewsScreenState(

    val error : String?=null,
    val category : String ="General",
    val isLoading : Boolean =false,
    val articleList : List<Article> = emptyList(),
    val article: Article?=null,
    val searchBarVisible : Boolean = false,
    val searchQuery :String =""
)
