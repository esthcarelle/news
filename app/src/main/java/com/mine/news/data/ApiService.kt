package com.mine.news.data

import com.mine.news.constants.ApiURL
import com.mine.news.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = ApiURL.API_KEY
    ): BaseModel
}
