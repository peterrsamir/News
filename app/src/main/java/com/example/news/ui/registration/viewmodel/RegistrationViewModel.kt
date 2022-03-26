package com.example.news.ui.registration.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.news.repository.Repository
import com.example.news.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val repository: Repository) : ViewModel() {

    fun register(name: String, phone: String, email: String, password: String) {
        var user = User(email, name, phone, password)
        CoroutineScope(Dispatchers.IO).launch {
            repository.addUser(user)
        }
    }

}