package com.ibtikar.mvvm_starter_koin_coroutines.ui


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.ibtikar.mvvm_starter_koin_coroutines.R

@BindingAdapter("loadImage")
fun loadImage(view: AppCompatImageView, url: String?) {
    view.load(url){
        error(R.drawable.placeholder)
    }
}
