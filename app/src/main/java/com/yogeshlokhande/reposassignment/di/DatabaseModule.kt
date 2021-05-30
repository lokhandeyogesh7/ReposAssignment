package com.yogeshlokhande.reposassignment.di

import android.content.Context
import androidx.room.Room
import com.yogeshlokhande.reposassignment.data.localdb.DAOAccess
import com.yogeshlokhande.reposassignment.data.localdb.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): NewsDatabase {
        return Room.databaseBuilder(
            appContext,
            NewsDatabase::class.java,
            "news.db"
        ).build()
    }

    @Provides
    fun provideLogDao(database: NewsDatabase): DAOAccess {
        return database.newsDao()
    }
}