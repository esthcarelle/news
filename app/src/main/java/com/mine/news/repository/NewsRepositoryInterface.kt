package com.mine.news.repository

import com.mine.news.model.BaseModel
import com.mine.news.network.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {
    suspend fun getHeadlines(source: String): Flow<DataState<BaseModel>>
}