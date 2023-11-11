package com.mine.news.model

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)