package com.example.news.repository

import com.example.example.News
import com.example.news.network.NewsApi
import com.example.news.network.NewsState
import retrofit2.Response

class Repo : RepoInterface {

    //   suspend fun getApiData():Response<News>{
//        return NewsApi.getInstance().getNews()
//    }
    override suspend fun getApiData(): NewsState {

        var res = NewsApi.getInstance().getNews()
        return if (res.isSuccessful) {
                NewsState.success(res.body()!!)

        } else {
                   NewsState.fail("failed to get data")
        }
    }

}