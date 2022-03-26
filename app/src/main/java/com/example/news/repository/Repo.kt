package com.example.news.repository

import com.example.news.network.NewsApi
import com.example.news.network.NewsState


class Repo : RepoInterface {


    override suspend fun getApiData(): NewsState {

        var res = NewsApi.getInstance().getNews()
        return if (res.isSuccessful) {
            NewsState.success(res.body()!!)

        } else {
            NewsState.fail("failed to get data")
        }
    }
}

