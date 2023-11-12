package com.mine.news.repository

import com.mine.news.data.ApiService
import com.mine.news.model.BaseModel
import com.mine.news.network.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService
) : MovieRepositoryInterface {

    override suspend fun getHeadlines(source: String, sortBy: String): Flow<UiState<BaseModel>> =
        flow {
            emit(UiState.Loading)
            try {
                val searchResult = apiService.getHeadlines(source, sortBy = sortBy)
                emit(UiState.Success(searchResult))

            } catch (e: Exception) {
                emit(UiState.Error(e.message))
            }
        }
}