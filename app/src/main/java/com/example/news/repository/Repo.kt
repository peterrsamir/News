package com.example.news.repository

import com.example.news.database.NewsDao
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import com.example.news.model.News
import com.example.news.network.NewsApi
import com.example.news.network.NewsState
import com.example.news.network.RetroInterface
import kotlinx.coroutines.flow.Flow


class Repo(var reto: RetroInterface, var newsDao: NewsDao) : RepositoryInterface {
    override suspend fun insertNews(cachedArticles: List<CachedArticles>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNews(): Flow<List<Articles>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFavorite(cachedArticles: CachedArticles) {
        newsDao.updateFavorite(cachedArticles)
    }

    override suspend fun deleteFavorite(cachedArticles: CachedArticles) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFavorites(): Flow<List<CachedArticles>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFav(url: String): Flow<CachedArticles> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByTitle(title: String): Flow<List<CachedArticles>> {
        return newsDao.searchByTitle(title)
    }


    override suspend fun getApiData(): NewsState {

        var res = reto.getNews()
        return if (res.isSuccessful) {
            NewsState.success(res.body()!!)

        } else {
            NewsState.fail("failed to get data")
        }
    }
}

