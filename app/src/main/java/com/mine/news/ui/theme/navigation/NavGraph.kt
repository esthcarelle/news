package com.mine.news.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.mine.news.model.Article
import com.mine.news.model.HeaderArgType
import com.mine.news.ui.theme.headerDetails.HeaderDetails
import com.mine.news.ui.theme.home.HeadlinesScreen
import com.mine.news.ui.theme.home.HeadlinesViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HeadersList.route) {
        composable(route = HeadersList.route) {
            val headlinesViewModel = hiltViewModel<HeadlinesViewModel>()
            HeadlinesScreen(headlinesViewModel = headlinesViewModel, onHeaderClick = { article ->
                navController.navigate(HeaderDetail.route + "/" + article)
            }
            )
        }
        composable(
            route = HeaderDetail.route + "/{article}",
            arguments = listOf(navArgument("article") {
                type = HeaderArgType()
            })
        ) { navBackStackEntry ->
            val article = navBackStackEntry.arguments?.getString("article")
                ?.let { Gson().fromJson(it, Article::class.java) }

            article?.let { HeaderDetails(article = it) }
        }
    }
}