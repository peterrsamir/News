package com.example.news.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class NewsApi{

    companion object{
         var retrofit: Retrofit?=null
        private val BASE_URL="https://newsapi.org/v2/"
        @Synchronized
        fun getInstance():RetroInterface{
            if(retrofit==null)
                retrofit=Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit!!.create(RetroInterface::class.java)
        }
    }

 }
