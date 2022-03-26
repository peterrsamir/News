package com.example.news.repository

import com.example.example.News
import com.example.news.network.NewsState
import retrofit2.Response

interface RepoInterface {
    suspend fun getApiData(): NewsState
}