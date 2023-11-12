package com.mine.news.ui.theme.headerDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mine.news.model.Article
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun HeaderDetails(article: Article, textFontFamily: FontFamily = FontFamily.Serif) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        CoilImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            imageModel = { article.urlToImage },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Movie detail",
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
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = article.title,
            fontFamily = textFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = article.description,
            fontFamily = textFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp).wrapContentSize(),
            text = article.content,
            fontFamily = textFontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
    }
}