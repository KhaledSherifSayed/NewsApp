package com.ibtikar.mvvm_starter_koin_coroutines.di

import com.ibtikar.mvvm_starter_koin_coroutines.ui.articlesList.ArticlesRepository
import org.koin.dsl.module

/**
 * Created by Meslmawy on 6/10/2021
 */

val repositoryModule = module {
        single {
            ArticlesRepository(get(),get())
        }
}