package com.ibtikar.mvvm_starter_koin_coroutines.di

import com.ibtikar.mvvm_starter_koin_coroutines.ui.articlesList.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Meslmawy on 6/10/2021
 */

val viewModelModule = module {
    viewModel {
        ArticlesViewModel(get(), get())
    }

}