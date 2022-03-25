package com.example.news.utils

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE email = :email AND password = :password ")
    fun login(email: String, password: String): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    fun changePassword(user: User)
}
