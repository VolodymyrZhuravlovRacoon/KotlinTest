package com.example.kotlintest.api

import retrofit2.Call

interface NewsAPI {
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}