package com.example.pgtestnews.ui.presentance.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pgtestnews.ui.presentance.component.article.ArticleScreen
import com.example.pgtestnews.ui.presentance.screen.NewsScreen
import com.example.pgtestnews.ui.presentance.screen.NewsViewModel

@Composable
fun NavGraph(navHostController: NavHostController){

    val argKey = "web_url"

    NavHost(navController = navHostController,
        startDestination ="news_screen"){
        composable(route="news_screen"){
            val viewModel : NewsViewModel = hiltViewModel()
            NewsScreen(
                state =viewModel.state,
                event =viewModel::event,
                onReadFullDescription ={url->
                    navHostController.navigate("article_screen?$argKey=$url")
                }
            )
        }
        composable(route="article_screen?$argKey={$argKey}",
            arguments = listOf(navArgument(name = argKey){
                type = NavType.StringType
            })
        ){
                ArticleScreen(url = it.arguments?.getString(argKey),
                    onBackPressed = { navHostController.navigateUp() })
        }
    }

}