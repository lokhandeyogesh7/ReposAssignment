package com.yogeshlokhande.reposassignment.data.model

import java.io.Serializable

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
) : Serializable {
    data class Articles(
        val source: Source,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String,
        val content: String
    ) : Serializable {
        data class Source(
            val id: String,
            val name: String
        ) : Serializable
    }
}