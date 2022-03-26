package com.example.news.database

import android.content.Context
import androidx.room.*
import com.example.news.model.CachedArticles


@Database(entities = [CachedArticles::class], version = 1)
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
    abstract fun getDao(): NewsDao
}