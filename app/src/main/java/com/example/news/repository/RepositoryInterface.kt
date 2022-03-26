package com.example.news.repository

import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import com.example.news.model.News
import com.example.news.network.NewsState
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {

    suspend fun insertNews(cachedArticles: List<CachedArticles>)
    suspend fun deleteAllDatabase()
    suspend fun getAllNews(): Flow<List<Articles>>
    suspend fun updateFavorite(cachedArticles: CachedArticles)
    suspend fun deleteFavorite(cachedArticles: CachedArticles)
    suspend fun getAllFavorites(): Flow<List<CachedArticles>>
    suspend fun getFav(url:String): Flow<CachedArticles>
    suspend fun searchByTitle(title:String): Flow<List<CachedArticles>>

    suspend fun getApiData(): NewsState

}