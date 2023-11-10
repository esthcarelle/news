package com.mine.news.ui.theme.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mine.news.model.BaseModel
import com.mine.news.network.DataState
import com.mine.news.repository.NewsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeadlinesViewModel @Inject constructor(private val repo: NewsRepository): ViewModel() {
    private val _headlinesContent: MutableState<DataState<BaseModel>?> = mutableStateOf(null)
    val headlinesContent = _headlinesContent

    fun getHeadlines(source: String) {
        viewModelScope.launch {
            repo.getHeadlines(source).onEach {
                _headlinesContent.value = it
            }.launchIn(viewModelScope)
        }
    }
}