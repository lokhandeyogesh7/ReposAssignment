package com.yogeshlokhande.reposassignment.ui.news

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yogeshlokhande.reposassignment.R
import com.yogeshlokhande.reposassignment.data.model.NewsLocaLdb
import com.yogeshlokhande.reposassignment.data.model.NewsResponse
import com.yogeshlokhande.reposassignment.isVisible
import com.yogeshlokhande.reposassignment.showSnackbar
import com.yogeshlokhande.reposassignment.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news), NewsAdapter.onItemSelected {

    private val TAG = "NewsFragment"
    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionBar()

        newsViewModel.fetchAllNews(COUNTRY_QUERY, APIKEY_QUERY)

        obServeOnlineNews()

        showProgressbar()

        showError()

        displayOfflineNews()
    }

    private fun displayOfflineNews() {
        newsViewModel.newsLocal.observe(viewLifecycleOwner, {
            requireView().showSnackbar("no connection showing offline results")
            setAdapter(it[0].articles)
        })
    }

    private fun showError() {
        newsViewModel.error.observe(viewLifecycleOwner, {
            requireContext().showToast(it)
        })
    }

    private fun showProgressbar() {
        newsViewModel.isLoading.observe(viewLifecycleOwner, {
            progress_bar.isVisible(it)
        })
    }

    private fun obServeOnlineNews() {
        newsViewModel.news.observe(viewLifecycleOwner, { response ->
            newsViewModel.deleteAllRecords()
            saveToLocalDb(response)
            setAdapter(response)
        })
    }

    private fun setUpActionBar() {
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar_news)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = "News"
    }

    private fun setAdapter(response: NewsResponse) {
        recycler_view.scheduleLayoutAnimation()
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        val adapter = NewsAdapter(requireContext(), response.articles, this@NewsFragment)
        recycler_view.adapter = adapter
    }

    private fun saveToLocalDb(response: NewsResponse) {
        val articles = NewsLocaLdb(response)
        lifecycleScope.launch {
            newsViewModel.inserDataLocally(articles)
        }
    }

    companion object {
        const val COUNTRY_QUERY = "in"
        const val APIKEY_QUERY = "874f6da3f60f41ac8b3aefdb28a2c881"
    }

    override fun onNewsItemSelected(article: NewsResponse.Articles) {
        findNavController().navigate(
            R.id.action_newsFragment_to_newsDetailsFragment,
            bundleOf("selected_news" to article)
        )
    }

}