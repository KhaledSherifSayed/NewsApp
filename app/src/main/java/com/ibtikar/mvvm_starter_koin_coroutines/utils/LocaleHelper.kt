package com.ibtikar.mvvm_starter_koin_coroutines.utils

import android.content.Context
import android.content.res.Configuration
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesUtils.Companion.CURRENT_LANGUAGE
import java.util.*

object LocaleHelper {

    fun setLocale(context: Context, lang: String = getLanguage(context)): Context {
        return updateResources(context, Locale(lang))
    }

    fun getLanguage(context: Context): String {
        val preferences = context.getSharedPreferences(
            context.getString(R.string.sharedPrefrence_name),
            Context.MODE_PRIVATE
        )
        val lang = preferences?.getString(CURRENT_LANGUAGE, null) ?: ""
        return if (lang.isEmpty()) {
            if (Locale.getDefault().language.equals(LanguageCodes.ARABIC, true)) {
                preferences.edit().putString(CURRENT_LANGUAGE, LanguageCodes.ARABIC).apply()
                LanguageCodes.ARABIC
            } else {
                preferences.edit().putString(CURRENT_LANGUAGE, LanguageCodes.ENGLISH).apply()
                LanguageCodes.ENGLISH
            }
        } else
            lang
    }

    private fun updateResources(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.setLocale(locale)
        configuration.fontScale = 1.0f
        return context.createConfigurationContext(configuration)
    }

}
