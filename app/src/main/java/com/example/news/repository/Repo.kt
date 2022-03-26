package com.example.news.repository

import com.example.news.model.News

class Repo:RepositoryInterface {
    override suspend fun insertNews(news: News) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNews() {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsByID(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllDatabase() {
        TODO("Not yet implemented")
    }
    
     override suspend fun getApiData(): Response<News> {
        return NewsApi.getInstance().getNews()
    }
}