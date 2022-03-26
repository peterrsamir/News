package com.example.news.network

import com.example.news.model.News
import retrofit2.Response
import retrofit2.http.GET

interface RetroInterface {
   @GET("top-headlines?country=eg&apiKey=cc73e45ff8d745df99c30e4aa7e33379")
   suspend fun getNews(): Response<News>

}