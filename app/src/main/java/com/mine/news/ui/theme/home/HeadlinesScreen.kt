package com.mine.news.ui.theme.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mine.news.model.Article
import com.mine.news.model.BaseModel
import com.mine.news.network.DataState
import com.mine.news.ui.theme.components.ArticleItem

@SuppressLint("SuspiciousIndentation")
@Composable
fun HeadlinesScreen() {
    val articlesViewModel = hiltViewModel<HeadlinesViewModel>()
    LaunchedEffect(key1 = 0) {
        articlesViewModel.getHeadlines("bbc-news")
    }
    Column {
        Text(text = "Headline")
        if (articlesViewModel.headlinesContent.value is DataState.Success)
            ArticlesList(articles = (articlesViewModel.headlinesContent.value as DataState.Success<BaseModel>).data.articles)
    }
}

@Composable
fun ArticlesList(articles: List<Article>) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 9.dp, end = 9.dp)
            .fillMaxWidth()
    ) {
        items(articles) { item ->
            ArticleItem(item)
        }
    }
}