package com.example.news.repository

import androidx.lifecycle.LiveData
import com.example.news.model.News
import com.example.news.model.User

interface RepositoryInterface {

    suspend fun insertNews(news: News)
    suspend fun getAllNews()
    suspend fun getNewsByID(id: Int)
    suspend fun deleteAllDatabase()

    //user dao methods
    /*fun login(email: String, password: String): LiveData<List<User>>

    suspend fun addUser(user: User)

    suspend fun changePassword(user: User)*/
}