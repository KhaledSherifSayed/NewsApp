package com.ibtikar.mvvm_starter_koin_coroutines.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.local.SharedPreferencesInterface
import com.ibtikar.mvvm_starter_koin_coroutines.ui.onboarding.OnBoardingActivity
import com.ibtikar.mvvm_starter_koin_coroutines.utils.getKoinInstance
import com.ibtikar.mvvm_starter_koin_coroutines.utils.intents.openActivity

class MainActivity : AppCompatActivity() {
    val sharedPreferences by getKoinInstance<SharedPreferencesInterface>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!sharedPreferences.introduction) {
            openActivity(OnBoardingActivity::class.java, finish = true)
        } else {
            setContentView(R.layout.activity_main)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}