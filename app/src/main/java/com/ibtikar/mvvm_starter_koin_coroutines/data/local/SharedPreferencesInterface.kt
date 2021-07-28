package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import kotlin.reflect.KClass

interface SharedPreferencesInterface {

    var language: String

    var countryFav: String

    var categoriesFav: String

    var introduction: Boolean

    var nightMode: Boolean


    fun clearData()
    fun getString(key: String): String?
    fun putString(key: String, value: String?)
    fun getBoolean(key: String): Boolean
    fun putBoolean(key: String, value: Boolean)
    fun <T> putObject(key: String, value: T)
    fun <T : Any> getObject(key: String, type: KClass<T>): T?
    fun putInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int = -1): Int
}
