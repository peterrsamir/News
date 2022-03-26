package com.example.news.network

import com.example.example.Articles
import com.example.example.News

sealed class NewsState{
    object isLoading:NewsState()
    data class success(var news: News):NewsState()
    data class fail(var msg: String):NewsState()

}
