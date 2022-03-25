package com.example.news.repository

import com.example.example.News
import com.example.news.network.NewsApi
import retrofit2.Response

class Repo : RepoInterface{

    //   suspend fun getApiData():Response<News>{
//        return NewsApi.getInstance().getNews()
//    }
    override suspend fun getApiData(): Response<News> {
        return NewsApi.getInstance().getNews()
    }

}