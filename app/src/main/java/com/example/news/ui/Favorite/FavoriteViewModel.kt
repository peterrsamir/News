package com.example.news.ui.Favorite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.CachedArticles
import com.example.news.repository.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(val repo: Repo) : ViewModel() {

    var favoriteList = MutableLiveData<List<CachedArticles>>()

    fun getAllFavorites() {

        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllFavorites().collect {
                Log.e("TAG", "getAllFavorites: ${it.size}", )
                favoriteList.postValue(it)
            }
        }
    }
}