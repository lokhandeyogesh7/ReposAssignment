package com.yogeshlokhande.reposassignment.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogeshlokhande.reposassignment.data.model.NewsLocaLdb
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(newsLocaLdb: NewsLocaLdb): Long

    @Query("SELECT * FROM News")
    suspend fun getAllNews(): MutableList<NewsLocaLdb>

    @Query("DELETE FROM News")
    suspend fun deleteAll()

}