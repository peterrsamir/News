package com.example.news.database

import androidx.room.*
import androidx.room.Dao
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(list: List<CachedArticles>)

    @Query("Delete From Articles")
    fun deleteAllDatabase()

   /* @Query("Select * From Articles Where id=:id")
    fun getNewsByID(id: Int): Flow<Articles>*/

    @Query("Select * From Articles")
    fun getAllNews(): Flow<List<Articles>>

    @Update
    fun updateFavorite(articles: CachedArticles)

}