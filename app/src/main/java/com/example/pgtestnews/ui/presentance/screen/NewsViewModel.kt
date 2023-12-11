package com.example.pgtestnews.ui.presentance.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgtestnews.domain.repository.Repository
import com.example.pgtestnews.errorHandle.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){

    var state by mutableStateOf(NewsScreenState())

    init {
        getTopHeadline(category = "General")
    }

    private var searchJob :Job ?=null

    fun event(event : NewScreenEvent){
        when(event){
            is NewScreenEvent.OnCardClick -> {
                state = state.copy(article = event.article)
            }
            is NewScreenEvent.OnCategory -> {
                state = state.copy(category = event.category)
                getTopHeadline(category = event.category)
            }
            is NewScreenEvent.OnSearchQuery -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000)
                    getSearchNews(query = event.query)
                }

            }
            NewScreenEvent.SearchIconClosed -> {
                state = state.copy(searchBarVisible = false)
                getTopHeadline(category = state.category)
            }
            NewScreenEvent.SearchIconOpened -> {
                state = state.copy(searchBarVisible = true, articleList = emptyList())

            }
        }
    }

    private fun getTopHeadline(category : String){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            state = when(val result = repository.getTopHeadline(category = category)){
                is Resource.Error -> {
                    state.copy(error = result.messgage ?:"",
                        isLoading = false,
                        articleList = emptyList()
                    )
                }

                is Resource.Success -> {
                    state.copy(articleList = result.data ?: emptyList(),
                        error = null,
                        isLoading = false)
                }
            }
        }
    }

    private fun getSearchNews(query : String){
        if(query.isEmpty()){
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.getSearchNews(query = query)
            state = when(result){
                is Resource.Error -> {
                    state.copy(error = result.messgage ?:"",
                        isLoading = false,
                        articleList = emptyList()
                    )
                }

                is Resource.Success -> {
                    Log.d("myTag","query Sucess $result")

                    state.copy(articleList = result.data ?: emptyList(),
                        error = null,
                        isLoading = false)
                }
            }
        }
    }

}