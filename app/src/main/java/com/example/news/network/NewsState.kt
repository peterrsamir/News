package com.example.news.network

import com.example.news.model.News

sealed class NewsState{
    object isLoading:NewsState()
    data class success(var news: News):NewsState()
    data class fail(var msg: String):NewsState()

}
