package com.yogeshlokhande.reposassignment.ui.newsdetails

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yogeshlokhande.reposassignment.R
import com.yogeshlokhande.reposassignment.data.model.NewsResponse
import com.yogeshlokhande.reposassignment.getFormattedDate
import com.yogeshlokhande.reposassignment.ui.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_details.*


@AndroidEntryPoint
class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedNews = arguments?.get("selected_news") as NewsResponse.Articles

        setUpActionBar(selectedNews)

        setDataToView(selectedNews)

    }

    private fun setDataToView(selectedNews: NewsResponse.Articles) {
        news_desc.text = selectedNews.description

        news_det_date.text = "Published at: ${getFormattedDate(selectedNews.publishedAt)}"

        news_det_link.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedNews.url))
            startActivity(browserIntent)
        }

        news_title.text = selectedNews.title

        Glide.with(requireContext())
            .load(selectedNews.urlToImage)
            .placeholder(android.R.drawable.ic_media_play)
            .into(img_news_details)
    }

    private fun setUpActionBar(selectedNews: NewsResponse.Articles) {
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setHomeButtonEnabled(true)
        toolbar.title = selectedNews.source.name
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }


}