package com.ibtikar.mvvm_starter_koin_coroutines.ui.favorite


import com.ibtikar.mvvm_starter_koin_coroutines.data.local.FavoriteArticlesDatabase
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseRepository
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders

/**
 * Created by Meslmawy on 6/10/2021
 */


class NewsFavoriteRepository(
    contextProviders: ContextProviders,
    private val favoriteArticlesDatabase: FavoriteArticlesDatabase
) : BaseRepository(contextProviders) {

    fun getArticleList() = networkHandler {
        favoriteArticlesDatabase.favoriteArticlesDao.getArticles()
    }


    suspend fun deleteArticleFromFavoriteList(id : String) = networkHandler {
        favoriteArticlesDatabase.favoriteArticlesDao.deleteById(id)
    }

}
