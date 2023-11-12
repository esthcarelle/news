package com.mine.news.ui.theme.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mine.news.model.Article
import com.mine.news.model.BaseModel
import com.mine.news.network.UiState
import com.mine.news.ui.theme.components.ArticlesList

@SuppressLint("SuspiciousIndentation")
@Composable
fun HeadlinesScreen(
    headlinesViewModel: HeadlinesViewModel = viewModel(),
    onHeaderClick: (Article) -> Unit = {}
) {

    val headlines by headlinesViewModel.uiState.collectAsState(UiState.Loading)

    val headLine by rememberSaveable {
        mutableStateOf(com.mine.news.BuildConfig.SOURCE)
    }

    val headLineName by rememberSaveable {
        mutableStateOf(com.mine.news.BuildConfig.SOURCE_NAME)
    }

    LaunchedEffect(key1 = 0) {
        headlinesViewModel.getHeadlines(headLine)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        when (headlines) {
            is UiState.Success -> {
                Text(
                    text = headLineName,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                ArticlesList(
                    articles = (headlines as UiState.Success<BaseModel>).data.articles,
                    onHeaderClick = onHeaderClick
                )
            }

            is UiState.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            )

            else -> Text(text = (headlines as UiState.Error).exceptionMessage.toString())
        }
    }
}

