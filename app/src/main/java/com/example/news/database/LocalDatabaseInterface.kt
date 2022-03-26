package com.example.news.database

import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseInterface {

    suspend fun insertNews(cachedArticles: List<CachedArticles>)
    suspend fun deleteAllDatabase()
//    suspend fun getNewsByID(id: Int):Flow<Articles>
    suspend fun getAllNews():Flow<List<Articles>>
    suspend fun updateFavorite(cachedArticles: CachedArticles)
}