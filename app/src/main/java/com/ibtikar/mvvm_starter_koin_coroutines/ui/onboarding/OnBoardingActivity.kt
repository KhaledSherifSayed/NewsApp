package com.ibtikar.mvvm_starter_koin_coroutines.ui.onboarding

import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.ActivityOnBoardingBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.MainActivity
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseActivity
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import com.ibtikar.mvvm_starter_koin_coroutines.utils.getKoinInstance
import com.ibtikar.mvvm_starter_koin_coroutines.utils.intents.openActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class OnBoardingActivity :
    BaseActivity<ActivityOnBoardingBinding, OnBoardingViewModel>(R.layout.activity_on_boarding) {

    override val viewModel: OnBoardingViewModel by viewModel()
    val categoriesFavList: ArrayList<String> = ArrayList()
    val sharedPreferences by getKoinInstance<SharedPreferencesInterface>()

    override fun setup() {
        initCategoriesChips()
        nextButton.setOnClickListener{
            sharedPreferences.categoriesFav = categoriesFavList.joinToString(separator = ",")
            sharedPreferences.countryFav = ccp.selectedCountryNameCode
            sharedPreferences.introduction = true
            openActivity(MainActivity::class.java, finish = true)
        }
    }

    override fun render(state: ViewState) {

    }

    private fun initCategoriesChips() {
        // Create a Chip for each regionsList item.
        val chipGroup = binder.categoriesList
        val inflator = LayoutInflater.from(chipGroup.context)
        val categories = arrayListOf<String>(*resources.getStringArray(R.array.Categories))
        val children = categories.map { categoryName ->
            val chip = inflator.inflate(R.layout.region, chipGroup, false) as Chip
            chip.text = categoryName
            chip.tag = categoryName.toLowerCase()
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

