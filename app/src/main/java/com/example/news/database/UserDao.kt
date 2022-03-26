package com.example.news.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE email = :email AND password = :password ")
    suspend fun login(email: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun changePassword(user: User): Int
}
