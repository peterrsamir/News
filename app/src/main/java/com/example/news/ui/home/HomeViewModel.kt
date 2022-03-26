package com.example.news.ui.home

import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.News
import com.example.news.network.NewsApi
import com.example.news.network.NewsState
import com.example.news.repository.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (var repo:Repo): ViewModel() {



   private val _newsMutableData:MutableLiveData<NewsState> = MutableLiveData()
    val newsLiveData:LiveData<NewsState> = _newsMutableData


    fun getData()
    {
        _newsMutableData.value=NewsState.isLoading
        viewModelScope.launch() {
            var res=repo.getApiData()
                _newsMutableData.value=res
        }
    }


}