package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList


import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
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
        articlesAdapter = ArticlesAdapter(NewsItemClick {

        })
        articlesRV.adapter = articlesAdapter
    }

    override fun render(state: ViewState) {
        when (state) {
            is NewsViewState.onNewsResponse -> {
                articlesAdapter?.submitList(state.data?.toMutableList())
            }
        }
    }

}
