package com.ibtikar.mvvm_starter_koin_coroutines.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Meslmawy on 6/10/2021
 */

@Parcelize
data class NewsModelResponse(
    val author: String,
    val source: Source,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val content: String?,
    @SerializedName("publishedAt")
    val publishedDate: String,
) : Parcelable


@Parcelize
data class Source(
    val id: String?,
    val name: String
) : Parcelable