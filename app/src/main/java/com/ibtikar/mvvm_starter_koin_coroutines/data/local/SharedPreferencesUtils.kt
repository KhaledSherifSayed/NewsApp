package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ibtikar.mvvm_starter_koin_coroutines.R
import kotlin.reflect.KClass

class SharedPreferencesUtils constructor(
    private val gson: Gson,
    context: Context
) : SharedPreferencesInterface {


    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.sharedPrefrence_name),
            Context.MODE_PRIVATE
        )


    override var language: String
        get() {
            return getString(CURRENT_LANGUAGE)
        }
        set(value) {
            putString(CURRENT_LANGUAGE, value)
        }

    override var countryFav: String
        get() {
            return getString(FAVORITE_COUNTRY)
        }
        set(value) {
            putString(FAVORITE_COUNTRY, value)
        }

    override var categoriesFav: String
        get() {
            return getString(FAVORITE_CATEGORIES)
        }
        set(value) {
            putString(FAVORITE_CATEGORIES, value)
        }

    override var introduction: Boolean
        get() {
            return getBoolean(INTRODUCTION)
        }
        set(value) {
            putBoolean(INTRODUCTION, value)
        }

    override var nightMode: Boolean
        get() {
            return getBoolean(NIGHT_MODE)
        }
        set(value) {
            putBoolean(NIGHT_MODE, value)
        }

    override fun putString(key: String, value: String?) {
        mPrefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return mPrefs.getString(key, null) ?: ""
    }

    override fun putBoolean(key: String, value: Boolean) {
        mPrefs.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return mPrefs.getBoolean(key, false)
    }

    override fun putInt(key: String, value: Int) {
        mPrefs.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return mPrefs.getInt(key, defaultValue)
    }

    override fun <T> putObject(key: String, value: T) {
        mPrefs.edit().putString(key, gson.toJson(value)).apply()
    }

    override fun <T : Any> getObject(key: String, type: KClass<T>): T? {
        val s = mPrefs.getString(key, null) ?: return null
        return gson.fromJson(s, type.java)
    }

    override fun clearData() {
        val currentLanguage = getString(CURRENT_LANGUAGE)
        mPrefs.edit().clear().apply()
        putString(CURRENT_LANGUAGE, currentLanguage)
    }

    companion object {
        const val CURRENT_LANGUAGE = "CURRENT_LANGUAGE"
        const val FAVORITE_COUNTRY = "FAVORITE_COUNTRY"
        const val FAVORITE_CATEGORIES = "FAVORITE_CATEGORIES"
        const val INTRODUCTION = "INTRODUCTION"
        const val NIGHT_MODE = "NIGHT_MODE"

    }
}

