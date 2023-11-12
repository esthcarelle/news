package com.mine.news.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mine.news.model.Article

@Composable
fun ArticlesList(articles: List<Article>, onHeaderClick: (Article) -> Unit = {}) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(articles) { item ->
            ArticleItem(item, onHeaderClick = onHeaderClick)
        }
    }
}