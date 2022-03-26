package com.example.news.database

import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseInterface {

    suspend fun insertNews(cachedArticles: List<CachedArticles>)
    suspend fun deleteAllDatabase()
    suspend fun getAllNews():Flow<List<Articles>>
    suspend fun updateFavorite(cachedArticles: CachedArticles)
    suspend fun deleteFavorite(cachedArticles: CachedArticles)
    suspend fun getAllFavorites():Flow<List<CachedArticles>>
    suspend fun getFav(url:String):Flow<CachedArticles>
//    suspend fun searchByTitle(title:String):Flow<CachedArticles>
}