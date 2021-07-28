package com.ibtikar.mvvm_starter_koin_coroutines.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Meslmawy on 6/10/2021
 */

@Parcelize
data class MediaArticleItemResponse(
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String,
    val approved_for_syndication: Int,
    @SerializedName("media-metadata")
    val mediaMetaDataResponse: List<MediaMetaDataResponse>?
) : Parcelable

@Parcelize
data class MediaMetaDataResponse(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int,
): Parcelable