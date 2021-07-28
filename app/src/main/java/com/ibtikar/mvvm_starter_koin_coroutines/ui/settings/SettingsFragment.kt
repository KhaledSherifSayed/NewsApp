package com.ibtikar.mvvm_starter_koin_coroutines.ui.settings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.navigation.findNavController
import com.google.android.material.chip.Chip
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.FavoriteListFragmentBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragment
import com.ibtikar.mvvm_starter_koin_coroutines.utils.getKoinInstance
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.android.synthetic.main.settings_fragment.ccp
import kotlinx.android.synthetic.main.toolbar_settings.view.*
import java.util.*


/**
 * Created by Meslmawy on 6/10/2021
 */

class SettingsFragment :
    BaseFragment<FavoriteListFragmentBinding>(R.layout.settings_fragment) {

    private val sharedPreferences by getKoinInstance<SharedPreferencesInterface>()
    private var searchCategoriesFavList: ArrayList<String> = ArrayList()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            back()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings_toolbar.backImage.setOnClickListener { back() }
        ccp.setCountryForNameCode(sharedPreferences.countryFav)
        searchCategoriesFavList.clear()
        searchCategoriesFavList.addAll(sharedPreferences.categoriesFav.split(","))
        initCategoriesChips()
    }


    private fun back() {
        sharedPreferences.countryFav = ccp.selectedCountryNameCode.toLowerCase(Locale.ROOT)
        view?.findNavController()?.navigateUp()
    }

    private fun initCategoriesChips() {
        // Create a Chip for each regionsList item.
        val chipGroup = settingscategoriesList
        val inflator = LayoutInflater.from(chipGroup.context)
        val categories = arrayListOf<String>(*resources.getStringArray(R.array.Categories))
        val children = categories.map { categoryName ->
            val chip = inflator.inflate(R.layout.region, chipGroup, false) as Chip
            chip.text = categoryName
            chip.tag = categoryName.toLowerCase(Locale.ROOT)
            chip.isChecked = searchCategoriesFavList.contains(chip.tag)
            chip
        }
        chipGroup.removeAllViews()
        for (chip in children) {
            chip.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    searchCategoriesFavList.add(chip.tag as String)
                } else {
                    if (searchCategoriesFavList.size > 3) {
                        searchCategoriesFavList.remove(chip.tag as String)
                    } else {
                        chip.isChecked = true
                        Toast.makeText(
                            context,
                            "three favorite categories as minimum",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                sharedPreferences.categoriesFav =
                    searchCategoriesFavList.joinToString(separator = ",")
            }
            chipGroup.addView(chip)
        }
    }
}
