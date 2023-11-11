package com.mine.news.repository

import com.mine.news.data.ApiService
import com.mine.news.model.BaseModel
import com.mine.news.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService
) : MovieRepositoryInterface {

    override suspend fun getHeadlines(source: String): Flow<DataState<BaseModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.getHeadlines(source)
            emit(DataState.Success(searchResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}