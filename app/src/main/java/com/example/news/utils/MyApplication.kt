package com.example.news.utils

import android.app.Application
import com.example.news.database.DataBaseBuilder
import com.example.news.repository.Repository

class MyApplication : Application() {
    private val database by lazy { DataBaseBuilder.getInstance(this) }
    val usersRepository by lazy { Repository(database.getUsersDao()) }
}