package com.yogeshlokhande.reposassignment.data.localdb

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yogeshlokhande.reposassignment.data.model.NewsResponse
import java.io.Serializable


class Converters : Serializable {

    @TypeConverter
    fun toString(newsResponse: NewsResponse): String {
        return Gson().toJson(newsResponse)
    }

    @TypeConverter
    fun fromString(newsResponse: String): NewsResponse {
        return Gson().fromJson(newsResponse, NewsResponse::class.java)
    }
}