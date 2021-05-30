package com.yogeshlokhande.reposassignment.ui.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogeshlokhande.reposassignment.data.NetworkHelper
import com.yogeshlokhande.reposassignment.data.RetrofitRepository
import com.yogeshlokhande.reposassignment.data.model.NewsLocaLdb
import com.yogeshlokhande.reposassignment.data.model.NewsResponse
import kotlinx.coroutines.launch


class NewsViewModel @ViewModelInject constructor(
    private val repository: RetrofitRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _news: MutableLiveData<NewsResponse> = MutableLiveData()
    var _news_local = MutableLiveData<List<NewsLocaLdb>>()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val news: LiveData<NewsResponse>
        get() = _news
    val newsLocal: LiveData<List<NewsLocaLdb>>
        get() = _news_local

    fun fetchAllNews(COUNTRY_QUERY: String, APIKEY_QUERY: String) = viewModelScope.launch {
        isLoading.postValue(true)
        try {
            if (networkHelper.isNetworkConnected()) {
                _news.value = repository.fetchNews(COUNTRY_QUERY, APIKEY_QUERY)
            } else {
                _news_local.value = repository.fetchSavedNews()
            }
            isLoading.postValue(false)
        } catch (e: Exception) {
            e.printStackTrace()
            isLoading.postValue(false)
            error.postValue(e.localizedMessage)
        }
    }

    fun inserDataLocally(articles: NewsLocaLdb) = viewModelScope.launch {
        isLoading.postValue(true)
        try {
            repository.inserDataLocally(articles)
            isLoading.postValue(false)
        } catch (e: Exception) {
            e.printStackTrace()
            isLoading.postValue(false)
            error.postValue(e.localizedMessage)
        }
    }

    fun deleteAllRecords() = viewModelScope.launch {
        isLoading.postValue(true)
        try {
            repository.deleteAllRecords()
            isLoading.postValue(false)
        } catch (e: Exception) {
            e.printStackTrace()
            isLoading.postValue(false)
            error.postValue(e.localizedMessage)
        }
    }

}