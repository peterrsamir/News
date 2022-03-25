package com.example.news.repository

import com.example.example.News
import retrofit2.Response

interface RepoInterface {
    suspend fun getApiData():Response<News>
}