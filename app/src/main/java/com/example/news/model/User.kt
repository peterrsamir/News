package com.example.news.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "users")
data class User(@PrimaryKey(autoGenerate = false) @NonNull @ColumnInfo(name = "email")val email:String,
                @ColumnInfo(name = "name")val name:String,
                @ColumnInfo(name = "phone")val phone:String,
                @ColumnInfo(name = "password")var password:String
): Serializable
