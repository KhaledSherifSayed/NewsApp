package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Meslmawy on 6/10/2021
 */

@Entity
data class ArticleEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val actorId:Int,
    val name: String?,
    val popularity: Double?,
    val known_for_department: String?,
    val gender: Int?,
    val profile_path : String?,
    val adult: Boolean?,
    val description: String?)
