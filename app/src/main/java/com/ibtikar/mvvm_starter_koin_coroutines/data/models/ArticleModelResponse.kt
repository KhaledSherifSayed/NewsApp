package com.ibtikar.mvvm_starter_koin_coroutines.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.math.BigInteger

/**
 * Created by Meslmawy on 6/10/2021
 */

@Parcelize
data class ArticleModelResponse(
    val uri : String,
    val url : String,
    val id : BigInteger,
    @SerializedName("asset_id")
    val assetId : BigInteger,
    val source : String,
    @SerializedName("published_date")
    val publishedDate : String,
    val updated : String,
    val section : String,
    val subsection : String,
    val nytdsection : String,
    @SerializedName("adx_adx_keywordswords")
    val adxKeywordswords : String,
    val column : String,
    val byline : String,
    val type : String,
    val title : String,
    val abstract : String,
    val media : List<MediaArticleItemResponse>,
) : Parcelable {
    fun imageUrl(): String? {
        return if(media.isNotEmpty())
            media[0].mediaMetaDataResponse?.get(2)?.url
        else
            ""
    }
}