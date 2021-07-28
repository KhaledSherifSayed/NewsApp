package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList


import com.ibtikar.mvvm_starter_koin_coroutines.data.local.ArticleEntity
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.FavoriteArticlesDatabase
import com.ibtikar.mvvm_starter_koin_coroutines.data.network.ApiService
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseRepository
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders

/**
 * Created by Meslmawy on 6/10/2021
 */


class NewsRepository(
    contextProviders: ContextProviders,
    private val apiService: ApiService,
    private val favoriteArticlesDatabase: FavoriteArticlesDatabase
) : BaseRepository(contextProviders) {

    fun getArticleList(country: String, category: String) = networkHandler {
        apiService.getAllArticles(country, category)
    }

    fun checkItem(article: ArticleEntity) = networkHandler {
        favoriteArticlesDatabase.favoriteArticlesDao.exists(article.author + article.title)
    }

    suspend fun addArticleToFavoriteList(article: ArticleEntity) = networkHandler {
        favoriteArticlesDatabase.favoriteArticlesDao.insert(article)
    }
}
