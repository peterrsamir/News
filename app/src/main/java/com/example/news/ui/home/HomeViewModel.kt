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
import com.example.news.repository.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel (var repo:Repo): ViewModel() {



   private val _newsMutableData:MutableLiveData<News> = MutableLiveData()
    val newsLiveData:LiveData<News> = _newsMutableData


    fun getData()
    {
        viewModelScope.launch() {
            var res=repo.getApiData()
            if(res.isSuccessful)
                _newsMutableData.value=res.body()
        }
    }


}