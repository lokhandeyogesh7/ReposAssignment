package com.yogeshlokhande.reposassignment.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yogeshlokhande.reposassignment.data.model.NewsLocaLdb

@Database(entities = [NewsLocaLdb::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): DAOAccess
}