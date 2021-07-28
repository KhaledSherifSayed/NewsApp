package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.NewsModelResponse

/**
 * Created by Meslmawy on 6/10/2021
 */

@Entity
data class ArticleEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val articleId: String?,
    val title: String?,
    val author: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val content: String?,
    val publishedDate: String?,
    val source: String?
)


fun NewsModelResponse.toFavoriteArticleEntity() = ArticleEntity(
    articleId = author + title,
    title = title,
    author = author,
    description = description,
    url = url,
    urlToImage = urlToImage,
    content = content,
    publishedDate = publishedDate,
    source = source?.name
)
