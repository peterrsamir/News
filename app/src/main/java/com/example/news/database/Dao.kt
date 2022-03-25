package com.example.news.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import com.example.news.model.Favorite
import com.example.news.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(list: List<CachedArticles>)

    @Query("Delete From Articles")
    fun deleteAllDatabase()

    @Query("Select * From Articles Where id=:id")
    fun getNewsByID(id: Int): Flow<Articles>

    @Query("Select * From Articles")
    fun getAllNews(): Flow<List<Articles>>

    @Insert
    fun insertFavorite(favorite: Favorite)

    @Query("Select * from Favorite")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query("Delete from favorite where id=:id")
    fun deleteFavorite(id: Int)
}