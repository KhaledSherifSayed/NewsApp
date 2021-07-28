package com.ibtikar.mvvm_starter_koin_coroutines.utils

import android.content.Context

class ResourcesHandler(private val context: Context) {
    fun getString(stringId : Int) = context.getString(stringId)
}