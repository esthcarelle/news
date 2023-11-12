package com.mine.news.ui.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mine.news.model.Article
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun ArticleItem(article: Article, onHeaderClick: (Article) -> Unit = {}) {
    Column(modifier = Modifier
        .padding(16.dp)
        .clickable { onHeaderClick.invoke(article) }) {
        CoilImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            imageModel = { article.urlToImage },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center,
                contentDescription = "Header detail",
                colorFilter = null,
            ),
            component = rememberImageComponent {
                +CircularRevealPlugin(
                    duration = 800
                )
                +ShimmerPlugin(
                    baseColor = Color.Gray,
                    highlightColor = Color.Black
                )
            },
        )
        Text(text = article.title)
    }
}