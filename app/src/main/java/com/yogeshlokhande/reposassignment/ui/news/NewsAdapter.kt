package com.yogeshlokhande.reposassignment.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogeshlokhande.reposassignment.R
import com.yogeshlokhande.reposassignment.data.model.NewsResponse
import com.yogeshlokhande.reposassignment.getFormattedDate


class NewsAdapter(
    private val context: Context?,
    private val mData: List<NewsResponse.Articles>,
    private val onItemselected: onItemSelected
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = mData[position]
        if (news != null) {
            holder.title_news.text = news.title
            holder.desc_news.text = news.description
            holder.date_news.text = getFormattedDate(news.publishedAt)
            context?.let {
                Glide.with(it)
                    .load(news.urlToImage)
                    .placeholder(android.R.drawable.ic_media_play)
                    .into(holder.img_news)
            }

            holder.itemView.setOnClickListener {
                onItemselected.onNewsItemSelected(news)
            }
        }
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mData?.size!!
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title_news: TextView = itemView.findViewById(R.id.title_news)
        var date_news: TextView = itemView.findViewById(R.id.date_news)
        var desc_news: TextView = itemView.findViewById(R.id.desc_news)
        var img_news: ImageView = itemView.findViewById(R.id.img_news)
    }

    interface onItemSelected {
        fun onNewsItemSelected(article: NewsResponse.Articles)
    }
}