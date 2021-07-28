package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.toFavoriteArticleEntity
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.NewsListFragmentBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragmentWithBusiness
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import com.ibtikar.mvvm_starter_koin_coroutines.utils.getKoinInstance
import kotlinx.android.synthetic.main.news_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Meslmawy on 6/10/2021
 */

class NewsListFragment :
    BaseFragmentWithBusiness<NewsListFragmentBinding, NewsViewModel>(R.layout.news_list_fragment) {

    private var articlesAdapter: ArticlesAdapter? = null
    val sharedPreferences by getKoinInstance<SharedPreferencesInterface>()

    override val viewModel: NewsViewModel by viewModel()

    override fun setup() {
        setupAdapter()
        viewModel.getMostSharedArticles(
            sharedPreferences.countryFav,
            sharedPreferences.categoriesFav.split(",")
        )
    }

    private fun setupAdapter() {
        articlesAdapter = ArticlesAdapter(NewsItemClick { it, version ->
            when (version) {
                0 -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
                1 -> viewModel.addArticleToFavorite(it.toFavoriteArticleEntity())
            }

        })
        articlesRV.adapter = articlesAdapter
    }

    override fun render(state: ViewState) {
        when (state) {
            is NewsViewState.onNewsResponse -> {
                articlesAdapter?.submitList(state.data?.toMutableList())
            }
            is NewsViewState.onAddingFavoriteResponse -> {
                if (state.data == true)
                    Toast.makeText(
                        context,
                        "Article Added Successfully to your favorite",
                        Toast.LENGTH_LONG
                    ).show()
                else
                    Toast.makeText(context, "Article added before", Toast.LENGTH_LONG).show()
            }
        }
    }

}
