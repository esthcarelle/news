package com.mine.news.model

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.mine.news.utils.JsonNavType

data class Article(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}
class HeaderArgType : JsonNavType<Article>() {
    override fun fromJsonParse(value: String): Article = Gson().fromJson(value, Article::class.java)
    override fun Article.getJsonParse(): String = Gson().toJson(this)
}