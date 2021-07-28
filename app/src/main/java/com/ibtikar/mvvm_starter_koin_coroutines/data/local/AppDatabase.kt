package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Meslmawy on 6/10/2021
 */

@Dao
interface FavoriteArticlesDao {

    @Query("SELECT EXISTS (SELECT 1 FROM ArticleEntity WHERE articleId = :id)")
    suspend fun exists(id: String): Boolean

    @Query("select * from ArticleEntity")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert
    suspend fun insert(article : ArticleEntity)

    @Query("DELETE FROM ArticleEntity WHERE articleId = :id")
    suspend fun deleteById(id: String) :  Int
}

@Database(entities = [ArticleEntity::class], version = 2, exportSchema = false)
abstract class FavoriteArticlesDatabase : RoomDatabase() {
    abstract val favoriteArticlesDao: FavoriteArticlesDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteArticlesDatabase? = null

        fun getAppDataBase(context: Context): FavoriteArticlesDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    FavoriteArticlesDatabase::class.java,
                    "favoriteArticlesDB"
                ).build()
            }.also {
                INSTANCE = it
            }
        }
    }
}

