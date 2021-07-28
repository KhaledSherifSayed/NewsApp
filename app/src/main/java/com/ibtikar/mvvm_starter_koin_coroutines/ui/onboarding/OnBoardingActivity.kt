package com.ibtikar.mvvm_starter_koin_coroutines.ui.onboarding

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.chip.Chip
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
import com.ibtikar.mvvm_starter_koin_coroutines.ui.MainActivity
import com.ibtikar.mvvm_starter_koin_coroutines.utils.Constants.categories
import com.ibtikar.mvvm_starter_koin_coroutines.utils.LanguageCodes
import com.ibtikar.mvvm_starter_koin_coroutines.utils.LocaleHelper
import com.ibtikar.mvvm_starter_koin_coroutines.utils.getKoinInstance
import com.ibtikar.mvvm_starter_koin_coroutines.utils.intents.openActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*
import java.util.*
import kotlin.collections.ArrayList


class OnBoardingActivity : AppCompatActivity() {

    private val categoriesFavList: ArrayList<String> = ArrayList()
    private val sharedPreferences by getKoinInstance<SharedPreferencesInterface>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (sharedPreferences.nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        setContentView(R.layout.activity_on_boarding)
        initCategoriesChips()
        nextButton.setOnClickListener {
            sharedPreferences.categoriesFav = categoriesFavList.joinToString(separator = ",")
            sharedPreferences.countryFav = ccp.selectedCountryNameCode.toLowerCase(Locale.ROOT)
            sharedPreferences.introduction = true
            openActivity(MainActivity::class.java, finish = true)
        }
    }

    private fun initCategoriesChips() {
        // Create a Chip for each regionsList item.
        val chipGroup = categoriesList
        val inflator = LayoutInflater.from(chipGroup.context)
        val children = categories.map {
            val chip = inflator.inflate(R.layout.region, chipGroup, false) as Chip
            if(sharedPreferences.language == LanguageCodes.ARABIC)
               chip.text = it.titleAR
            else
                chip.text = it.titleEN
            chip.tag = it.titleEN.toLowerCase(Locale.ROOT)
            chip
        }
        chipGroup.removeAllViews()
        for (chip in children) {
            chip.setOnCheckedChangeListener { _, checked ->
                if (checked)
                    categoriesFavList.add(chip.tag as String)
                else
                    categoriesFavList.remove(chip.tag as String)

                nextButton.isEnabled = categoriesFavList.size > 2
            }
            chipGroup.addView(chip)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        LocaleHelper.setLocale(this)
        super.onConfigurationChanged(newConfig)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase!!))
    }


}

