package com.example.pgtestnews.ui.presentance.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNewsBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onSearchIconClick :() -> Unit
){
    TopAppBar(title = {
        Text(text = "News",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.SemiBold)
        },
        actions = {
            IconButton(onClick = {onSearchIconClick()}) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription =null )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
        )
}