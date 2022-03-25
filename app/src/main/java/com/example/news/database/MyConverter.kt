package com.example.news.database

import androidx.room.TypeConverter
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import com.example.news.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyConverter {

    @TypeConverter
    fun fromArticlesToString(articlesList: List<CachedArticles>): String {
        return Gson().toJson(articlesList)
    }

    @TypeConverter
    fun fromStringToArticle(news: String): List<CachedArticles> {
        val listType = object : TypeToken<List<CachedArticles>>() {}.type
        return Gson().fromJson(news, listType)
    }

    @TypeConverter
    fun fromSourceToString(source: Source): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun fromStringToSource(source: String): Source {
        return Gson().fromJson(source, Source::class.java)
    }

}