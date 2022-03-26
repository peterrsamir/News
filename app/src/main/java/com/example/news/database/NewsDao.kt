package com.example.news.database

import androidx.room.*
import androidx.room.Dao
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(list: List<CachedArticles>)

    @Query("Delete From Articles")
    suspend fun deleteAllDatabase()

    @Query("Select * From Articles")
    suspend fun getAllNews(): Flow<List<Articles>>

    @Query("Select * From Articles where isFavorite = 1")
    suspend fun getAllFavorites():Flow<List<CachedArticles>>

    @Query("select * From Articles where url=:url")
    suspend fun getFav(url:String):Flow<CachedArticles>

    @Delete
    suspend fun deleteFavorite(cachedArticles: CachedArticles)

    @Update
    suspend fun updateFavorite(articles: CachedArticles)

    @Query("select * from Articles where title like :title")
    suspend fun searchByTitle(title:String):Flow<CachedArticles>

}