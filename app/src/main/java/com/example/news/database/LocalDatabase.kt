package com.example.news.database

import android.content.Context
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import com.example.news.model.Favorite
import com.example.news.model.News
import kotlinx.coroutines.flow.Flow

class LocalDatabase(val context: Context) : LocalDatabaseInterface {


    var dao: Dao? = null
    init {
        val dataBaseBuilder: DataBaseBuilder = DataBaseBuilder.getInstance(context)
        dao = dataBaseBuilder.getDao()
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
        dao?.insertNews(cachedArticles)
    }


    override suspend fun deleteAllDatabase() {
        dao?.deleteAllDatabase()
    }

    override suspend fun getNewsByID(id: Int): Flow<Articles> {
        return dao?.getNewsByID(id)!!
    }

    override suspend fun getAllNews(): Flow<List<Articles>> {
        return dao?.getAllNews()!!
    }

    override suspend fun insertFavorite(favorite: Favorite) {
        dao?.insertFavorite(favorite)
    }

    override suspend fun getAllFavorite(): Flow<List<Favorite>> {
        return  dao?.getAllFavorites()!!
    }

    override suspend fun deleteFavorite(id: Int) {
        dao?.deleteFavorite(id)
    }
}