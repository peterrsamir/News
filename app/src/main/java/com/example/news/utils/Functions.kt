package com.example.news.utils

import android.util.Patterns

fun validatePassword(password : String) : Boolean{
    return (password.length >= 8 )
}

fun validatePhone(phone : String) : Boolean{
    return if (phone.length != 12) false
    else phone.startsWith("01")
}

fun validateEmail(email : String) : Boolean{
    return (Patterns.EMAIL_ADDRESS.matcher(email).matches())
}