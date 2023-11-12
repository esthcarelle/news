package com.mine.news.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mine.news.model.BaseModel
import com.mine.news.network.UiState
import com.mine.news.repository.NewsRepository
import com.mine.news.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val repo: NewsRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<BaseModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<BaseModel>> = _uiState

    fun getHeadlines(source: String) {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading
            repo.getHeadlines(source, "publishedAt")
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message)
                }
                .collect { it ->
                    _uiState.value = UiState.Success(it).data
                }
        }
    }
}