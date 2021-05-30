package com.yogeshlokhande.reposassignment.data

import com.yogeshlokhande.reposassignment.api.RetrofitApi
import com.yogeshlokhande.reposassignment.data.localdb.DAOAccess
import com.yogeshlokhande.reposassignment.data.model.NewsLocaLdb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRepository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val newsDAOAccess: DAOAccess
) {
    suspend fun fetchNews(COUNTRY_QUERY: String, APIKEY_QUERY: String) =
        retrofitApi.fetchAllNews(COUNTRY_QUERY, APIKEY_QUERY)

    suspend fun inserDataLocally(articles: NewsLocaLdb) = newsDAOAccess.insertData(articles)
    suspend fun fetchSavedNews() = newsDAOAccess.getAllNews()
    suspend fun deleteAllRecords() =newsDAOAccess.deleteAll()
}