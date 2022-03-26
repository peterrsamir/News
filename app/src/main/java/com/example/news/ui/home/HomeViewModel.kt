package com.example.news.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.Articles
import com.example.news.model.CachedArticles
import com.example.news.network.NewsState
import com.example.news.repository.Repo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel (var repo:Repo): ViewModel() {



   private val _newsMutableData:MutableLiveData<NewsState> = MutableLiveData()
    val newsLiveData:LiveData<NewsState> = _newsMutableData
    private val _searchMutableData:MutableLiveData<List<CachedArticles>> = MutableLiveData()
    val searchLiveData:LiveData<List<CachedArticles>> = _searchMutableData
    private val _allNewsMutableData:MutableLiveData<List<CachedArticles>> = MutableLiveData()
    val allNewsLiveData:LiveData<List<CachedArticles>> = _allNewsMutableData


    fun getData()
    {
        _newsMutableData.value=NewsState.isLoading
        viewModelScope.launch() {
            var res=repo.getApiData()
                _newsMutableData.value=res
        }
    }

    fun search(title:String){
     viewModelScope.launch {
         repo.searchByTitle(title).collect {
             _searchMutableData.value=it
         }
     }

    }
    fun insertNews(cachedArticles: List<CachedArticles>){
        viewModelScope.launch {
            repo.newsDao.insertNews(cachedArticles)
        }
    }
    fun startListening()
    {
        viewModelScope.launch {
            repo.newsDao.getAllNews().collect {
                _allNewsMutableData.value=it
            }
        }

    }

    fun insertFavorite(cachedArticles: CachedArticles){
        viewModelScope.launch {
            repo.updateFavorite(cachedArticles)
        }
    }


}