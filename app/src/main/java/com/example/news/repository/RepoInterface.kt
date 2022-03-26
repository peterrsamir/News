package com.example.news.repository

import com.example.news.network.NewsState

interface RepoInterface {
    suspend fun getApiData(): NewsState
}