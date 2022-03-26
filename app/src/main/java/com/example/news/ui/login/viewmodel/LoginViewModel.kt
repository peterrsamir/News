package com.example.news.ui.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.repository.Repository
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class LoginViewModel(private val repository: Repository) : ViewModel() {

    private val isLogin = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            if (repository.login(email, password) != null) {
                Log.i("TAG", "login: ${repository.login(email, password)}")
                isLogin.postValue(true)
            } else {
                isLogin.postValue(false)
            }
        }
    }

    fun observableLoginStatus(): LiveData<Boolean> = isLogin
}