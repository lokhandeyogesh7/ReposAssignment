package com.yogeshlokhande.reposassignment.data.model

import androidx.room.*
import com.google.gson.Gson
import com.yogeshlokhande.reposassignment.data.localdb.Converters
import retrofit2.Converter
import java.io.Serializable

@Entity(tableName = "News")
data class NewsLocaLdb(
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "articles")
    var articles: NewsResponse,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}
