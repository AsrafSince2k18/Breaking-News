package com.example.pgtestnews.ui.presentance.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.pgtestnews.domain.data.Article
import com.example.pgtestnews.ui.presentance.component.CategoryTabRow
import com.example.pgtestnews.ui.presentance.component.NewsBottomSheet
import com.example.pgtestnews.ui.presentance.component.NewsCard
import com.example.pgtestnews.ui.presentance.component.SearchBarNews
import com.example.pgtestnews.ui.presentance.component.TopNewsBar
import com.example.pgtestnews.ui.presentance.component.error.ErrorScreen
import com.example.pgtestnews.ui.presentance.component.shimmer.ShimmerEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun NewsScreen(
    state : NewsScreenState,
    event: (NewScreenEvent)->Unit,
    onReadFullDescription: (String) -> Unit
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val pagerState = rememberPagerState()
    val category = listOf(
        "General", "Entertainment", "Business", "Health", "Science", "Sports", "Technology"
    )
    val coroutine = rememberCoroutineScope()

    var bottomSheet by remember{
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val focus = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current

    val keyBoardControl = LocalSoftwareKeyboardController.current

    if(bottomSheet){
        ModalBottomSheet(
            onDismissRequest = { bottomSheet=false },
            sheetState = sheetState,
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 4.dp,
            content = {
                state.article?.let {article->
                    NewsBottomSheet(article = article,
                        onReadFullArticle = {articles->
                            onReadFullDescription(articles.url)
                            coroutine.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if(!sheetState.isVisible) bottomSheet=false
                            }
                        })
                }
            }

        )
    }


    LaunchedEffect(key1 = pagerState){
        snapshotFlow { pagerState.currentPage }.collect{
            event(NewScreenEvent.OnCategory(category=category[it]))
        }
    }


    LaunchedEffect(key1 = Unit){
            if(state.searchQuery.isNotEmpty()) {
                event(NewScreenEvent.OnSearchQuery(query = state.searchQuery))
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Crossfade(targetState = state.searchBarVisible, label = "") {isVisible->
            if(isVisible){
                Column {
                    SearchBarNews(
                        value = state.searchQuery,
                        onValueChange = {
                                        event(NewScreenEvent.OnSearchQuery(query = it))
                        },
                        onCloseIcon = {
                                      event(NewScreenEvent.SearchIconClosed)
                        },
                        onActionSearch = {
                            event(NewScreenEvent.OnSearchQuery(query = state.searchQuery))
                            keyBoardControl?.hide()
                            focusManager.clearFocus()

                        },
                        modifier = Modifier.focusRequester(focus)
                    )

                    ListShowNews(
                        state =state,
                        onRetry = {
                            event(NewScreenEvent.OnCategory(category=state.category))
                        },
                        onReadFullDescription ={article->
                            bottomSheet=true
                            event(NewScreenEvent.OnCardClick(article))
                        }
                    )
                }
            }else{
                Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TopNewsBar(scrollBehavior = scrollBehavior,
                            onSearchIconClick = {
                                coroutine.launch {
                                    delay(500)
                                    focus.requestFocus()
                                }
                                event(NewScreenEvent.SearchIconOpened)
                            })
                    }
                )
                {paddingValues ->
                    Column(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        CategoryTabRow(pagerState,
                            category = category,
                            onCategoryIndex = {
                                coroutine.launch {
                                    pagerState.scrollToPage(it)
                                }
                            })
                        HorizontalPager(state = pagerState, pageCount = category.size) {
                            ListShowNews(
                                state =state,
                                onRetry = {
                                    event(NewScreenEvent.OnCategory(category=category[it]))
                                },
                                onReadFullDescription ={article->
                                    bottomSheet=true
                                    event(NewScreenEvent.OnCardClick(article))
                                }
                            )
                        }

                    }
                }
            }

        }
    }




}


@Composable
fun ListShowNews(
    state: NewsScreenState,
    onRetry : () -> Unit,
    onReadFullDescription : (Article) ->Unit
){

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        if(state.isLoading){
            LazyColumn{
                items(10){
                    ShimmerEffect()
                }
            }
        }else if(state.error !=null){
            ErrorScreen(error = state.error,
                onRetry = onRetry)
        }else{
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(4.dp),
            ){
                items(state.articleList){
                    NewsCard(article = it,
                        onCardClick ={article->
                            onReadFullDescription(article)
                        } )
                }
            }
        }
    }

}