package com.example.pgtestnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.pgtestnews.ui.presentance.navgraph.NavGraph
import com.example.pgtestnews.ui.presentance.screen.NewsScreen
import com.example.pgtestnews.ui.presentance.screen.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           val navHostController = rememberNavController()
            NavGraph(navHostController = navHostController)
        }
    }
}

