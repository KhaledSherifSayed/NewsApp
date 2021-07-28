package com.ibtikar.mvvm_starter_koin_coroutines.ui.articlesList


import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.ArticleListFragmentBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragmentWithBusiness
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import kotlinx.android.synthetic.main.article_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Meslmawy on 6/10/2021
 */

class ArticlesListFragment :
    BaseFragmentWithBusiness<ArticleListFragmentBinding, ArticlesViewModel>(R.layout.article_list_fragment) {

    private var articlesAdapter: ArticlesAdapter? = null
    override val viewModel: ArticlesViewModel by viewModel()

    override fun setup() {
        setupAdapter()
        viewModel.getMostSharedArticles()
    }

    private fun setupAdapter() {
        articlesAdapter = ArticlesAdapter(ArticleClick {

        })
        articlesRV.adapter = articlesAdapter
    }

    override fun render(state: ViewState) {
        when (state) {
            is ArticlesViewState.onArticlesResponse -> {
                articlesAdapter?.submitList(state.data!!.toMutableList())
            }
        }
    }

}
