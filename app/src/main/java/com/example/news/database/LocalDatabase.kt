package com.example.news.database

import android.content.Context
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import kotlinx.coroutines.flow.Flow

class LocalDatabase(val context: Context) : LocalDatabaseInterface {


    var newsDao: NewsDao? = null
    init {
        val dataBaseBuilder: DataBaseBuilder = DataBaseBuilder.getInstance(context)
        newsDao = dataBaseBuilder.getDao()
    }

    companion object{
        private var localDatabase: LocalDatabase? =  null
        fun getInstance(context: Context):LocalDatabase{
            if(localDatabase == null){
                localDatabase = LocalDatabase(context)
            }
            return localDatabase!!
        }
    }

    override suspend fun insertNews(cachedArticles: List<CachedArticles>) {
        newsDao?.insertNews(cachedArticles)
    }


    override suspend fun deleteAllDatabase() {
        newsDao?.deleteAllDatabase()
    }

//    override suspend fun getNewsByID(id: Int): Flow<Articles> {
//        return newsDao?.getNewsByID(id)!!
//    }

    override suspend fun getAllNews(): Flow<List<Articles>> {
        return newsDao?.getAllNews()!!
    }

    override suspend fun updateFavorite(cachedArticles: CachedArticles) {
        newsDao?.updateFavorite(cachedArticles)
    }


}