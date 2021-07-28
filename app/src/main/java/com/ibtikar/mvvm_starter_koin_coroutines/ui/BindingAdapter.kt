package com.ibtikar.mvvm_starter_koin_coroutines.ui


import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import coil.load
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.utils.DAY_MONTH_YEAR_HOUR_MIN
import com.ibtikar.mvvm_starter_koin_coroutines.utils.YEAR_MONTH_DAY_T_TIME
import com.ibtikar.mvvm_starter_koin_coroutines.utils.toDateFormatted

@BindingAdapter("loadImage")
fun loadImage(view: AppCompatImageView, url: String?) {
    view.load(url) {
        error(R.drawable.placeholder)
    }
}


@BindingAdapter("formatDate")
fun formatArticleDate(view: AppCompatTextView, date: String?) {
    view.text = date?.toDateFormatted(YEAR_MONTH_DAY_T_TIME, DAY_MONTH_YEAR_HOUR_MIN)
}