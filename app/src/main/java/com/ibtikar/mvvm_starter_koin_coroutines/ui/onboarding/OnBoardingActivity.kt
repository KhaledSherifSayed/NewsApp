package com.ibtikar.mvvm_starter_koin_coroutines.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
import com.ibtikar.mvvm_starter_koin_coroutines.ui.MainActivity
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
        setContentView(R.layout.activity_on_boarding)
        initCategoriesChips()
        nextButton.setOnClickListener{
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
        val categories = arrayListOf<String>(*resources.getStringArray(R.array.Categories))
        val children = categories.map { categoryName ->
            val chip = inflator.inflate(R.layout.region, chipGroup, false) as Chip
            chip.text = categoryName
            chip.tag = categoryName.toLowerCase(Locale.ROOT)
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

}

