package com.example.news.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.news.model.CachedArticles
import com.example.news.model.Favorite
import com.example.news.model.News

@Database(entities = [CachedArticles::class, Favorite::class], version = 1)
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

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    abstract fun getDao(): Dao
}