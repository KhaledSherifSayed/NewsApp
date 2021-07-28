package com.ibtikar.mvvm_starter_koin_coroutines.utils

import com.ibtikar.mvvm_starter_koin_coroutines.data.models.CategoryModel

object LanguageCodes {
    const val ARABIC: String = "ar"
    const val ENGLISH: String = "en"
}

object Constants {
    val categories: List<CategoryModel> = listOf(
        CategoryModel(1, "General", "عام"),
        CategoryModel(2, "Business", "عمل"),
        CategoryModel(3, "Entertainment", "ترفيه"),
        CategoryModel(4, "Health", "صحة"),
        CategoryModel(5, "Science", "علوم"),
        CategoryModel(6, "Sports", "رياضات"),
        CategoryModel(7, "Technology", "تكنولوجيا")
    )
}

