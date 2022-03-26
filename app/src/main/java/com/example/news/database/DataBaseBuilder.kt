package com.example.news.database

import android.content.Context
import androidx.room.*
import com.example.news.model.CachedArticles
import com.example.news.model.User


@Database(entities = [CachedArticles::class, User::class], version = 2)
@TypeConverters(MyConverter::class)
abstract class DataBaseBuilder : RoomDatabase() {

    companion object {
        @Volatile
        private var dataBaseBuilder: DataBaseBuilder? = null

        @Synchronized
        fun getInstance(context: Context): DataBaseBuilder {
            if (dataBaseBuilder == null) {
                dataBaseBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseBuilder::class.java,
                    "News"
                ).fallbackToDestructiveMigration().build()
            }
            return dataBaseBuilder!!
        }
    }
    abstract fun getNewsDao(): NewsDao
    abstract fun getUsersDao(): UserDao
}