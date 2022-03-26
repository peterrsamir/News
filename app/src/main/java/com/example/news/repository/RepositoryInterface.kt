package com.example.news.repository

import com.example.news.model.News

interface RepositoryInterface {

    suspend fun insertNews(news: News)
    suspend fun getAllNews()
    suspend fun getNewsByID(id: Int)
    suspend fun deleteAllDatabase()
}