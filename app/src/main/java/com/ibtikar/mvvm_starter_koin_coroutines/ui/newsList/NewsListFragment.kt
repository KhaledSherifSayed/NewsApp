package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.toFavoriteArticleEntity
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.NewsModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.NewsListFragmentBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragmentWithBusiness
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import com.ibtikar.mvvm_starter_koin_coroutines.utils.*
import kotlinx.android.synthetic.main.news_list_fragment.*
import kotlinx.android.synthetic.main.toolbar_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


/**
 * Created by Meslmawy on 6/10/2021
 */

class NewsListFragment :
    BaseFragmentWithBusiness<NewsListFragmentBinding, NewsViewModel>(R.layout.news_list_fragment) {

    private var articlesAdapter: ArticlesAdapter? = null
    val sharedPreferences by getKoinInstance<SharedPreferencesInterface>()
    private var searchCategoriesFavList: ArrayList<String> = ArrayList()
    private var newsList = mutableListOf<NewsModelResponse>()
    override val viewModel: NewsViewModel by viewModel()

    override fun setup() {
        setupAdapter()
        viewModel.getMostSharedArticles(
            sharedPreferences.countryFav,
            sharedPreferences.categoriesFav.split(",")
        )
        searchIV.setOnClickListener {
            hide(toolbar_title, settingsIV, favoriteIV, searchIV)
            show(backImage, searchResultsET, searchCategoriesScrollview)
        }

        backImage.setOnClickListener {
            show(toolbar_title, settingsIV, favoriteIV, searchIV)
            hide(backImage, searchResultsET, searchCategoriesScrollview)
            viewModel.getMostSharedArticles(
                sharedPreferences.countryFav,
                sharedPreferences.categoriesFav.split(",")
            )
            searchCategoriesFavList.clear()
            searchCategoriesFavList.addAll(sharedPreferences.categoriesFav.split(","))
            initCategoriesChips()
            searchResultsET.clear()
        }

        searchResultsET.afterTextChanged {
            filter(it)
        }

        searchCategoriesFavList.addAll(sharedPreferences.categoriesFav.split(","))
        initCategoriesChips()
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
                newsList.clear()
                newsList.addAll(state.data?.toMutableList()!!)
                if (searchResultsET.visibility == View.VISIBLE)
                    filter(searchResultsET.text.toString())
                else
                    articlesAdapter?.submitList(newsList)
            }
            is NewsViewState.onAddingFavoriteResponse -> {
                if (state.data == true)
                    Toast.makeText(
                        context,
                        getString(R.string.favorite_success_msg),
                        Toast.LENGTH_LONG
                    ).show()
                else
                    Toast.makeText(
                        context,
                        getString(R.string.favorite_check_msg),
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    private fun initCategoriesChips() {
        // Create a Chip for each regionsList item.
        val chipGroup = searchCategoriesChips
        val inflator = LayoutInflater.from(chipGroup.context)
        val categories = arrayListOf<String>(*resources.getStringArray(R.array.Categories))
        val children = categories.map { categoryName ->
            val chip = inflator.inflate(R.layout.region, chipGroup, false) as Chip
            chip.text = categoryName
            chip.tag = categoryName.toLowerCase(Locale.ROOT)
            chip.isChecked = searchCategoriesFavList.contains(chip.tag)
            chip
        }
        chipGroup.removeAllViews()
        for (chip in children) {
            chip.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    searchCategoriesFavList.add(chip.tag as String)
                    refreshSearchedCategories()
                } else {
                    if (searchCategoriesFavList.size > 1) {
                        searchCategoriesFavList.remove(chip.tag as String)
                        refreshSearchedCategories()
                    } else {
                        chip.isChecked = true
                        Toast.makeText(
                            context,
                            getString(R.string.search_check_msg),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }
            chipGroup.addView(chip)
        }
    }

    private fun refreshSearchedCategories() {
        viewModel.getMostSharedArticles(
            sharedPreferences.countryFav,
            searchCategoriesFavList
        )
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<NewsModelResponse> = ArrayList()
        for (item in newsList) {
            if (item.title.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(item)
            }
        }
        articlesAdapter?.submitList(filteredList.toMutableList())
    }
}
