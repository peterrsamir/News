package com.example.news.ui.registration.viewmodel

import androidx.lifecycle.ViewModel
import com.example.news.Repository
import com.example.news.User

class RegistrationViewModel(val repository: Repository): ViewModel() {

    fun register(name: String, phone : String, email: String, password: String){
        var user = User(email, name, phone, password)
       // repository.register(user)
    }
}