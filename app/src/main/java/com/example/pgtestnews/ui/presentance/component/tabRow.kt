package com.example.pgtestnews.ui.presentance.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryTabRow(
    pagerState: PagerState,
    category : List<String>,
    onCategoryIndex:(Int) -> Unit
){
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        containerColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        category.forEachIndexed { index, s ->
            Tab(
                selected =pagerState.currentPage == index,
                onClick = { onCategoryIndex(index) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.secondary
            ) {
                Text(text = s,
                    fontSize = 16.sp,
                   modifier = Modifier.padding(4.dp))
            }
        }
    }

}