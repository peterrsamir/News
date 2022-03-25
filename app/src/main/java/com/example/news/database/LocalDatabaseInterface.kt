package com.example.news.database

import com.example.news.model.Articles
import com.example.news.model.Favorite
import com.example.news.model.News
import com.example.news.model.CachedArticles
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseInterface {

    suspend fun insertNews(cachedArticles: List<CachedArticles>)
    suspend fun deleteAllDatabase()
    suspend fun getNewsByID(id: Int):Flow<Articles>
    suspend fun getAllNews():Flow<List<Articles>>
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun getAllFavorite():Flow<List<Favorite>>
    suspend fun deleteFavorite(id: Int)
}