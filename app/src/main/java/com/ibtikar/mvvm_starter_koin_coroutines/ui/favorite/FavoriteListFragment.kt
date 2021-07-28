package com.ibtikar.mvvm_starter_koin_coroutines.ui.favorite


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.navigation.findNavController
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.toFavoriteArticleEntity
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.FavoriteListFragmentBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragmentWithBusiness
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList.ArticlesAdapter
import com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList.NewsItemClick
import kotlinx.android.synthetic.main.favorite_list_fragment.*
import kotlinx.android.synthetic.main.toolbar_favorite.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Meslmawy on 6/10/2021
 */

class FavoriteListFragment :
    BaseFragmentWithBusiness<FavoriteListFragmentBinding, FavoriteNewsViewModel>(R.layout.favorite_list_fragment) {

    private var articlesAdapter: FavoriteArticlesAdapter? = null
    override val viewModel: FavoriteNewsViewModel by viewModel()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            back()
        }
    }

    override fun setup() {
        setupAdapter()
        viewModel.getFavoriteArticles()
        favorite_toolbar.backImage.setOnClickListener { back() }
    }

    private fun setupAdapter() {
        articlesAdapter = FavoriteArticlesAdapter(NewsItemClick { it, version ->
            when (version) {
                0 -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
                1 -> viewModel.removeArticleFromFavorite(it.author + it.title)
            }
        })
        articlesRV.adapter = articlesAdapter
    }

    override fun render(state: ViewState) {
        when (state) {
            is FavoriteNewsViewState.onNewsResponse -> {
                articlesAdapter?.submitList(state.data)
            }
            is FavoriteNewsViewState.onDeletingFavoriteResponse -> {
                Toast.makeText(
                    context,
                    "Article is deleted from Favorite",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun back() {
        view?.findNavController()?.navigateUp()
    }

}
