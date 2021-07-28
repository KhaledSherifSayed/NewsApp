package com.ibtikar.mvvm_starter_koin_coroutines.data.local

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Meslmawy on 6/10/2021
 */

@Dao
interface ArticlesDao {

    @Query("select * from ArticleEntity")
    fun getActors(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg articles: ArticleEntity)
}

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract val articlesDao: ArticlesDao

    companion object {
        @Volatile
        private var INSTANCE: ArticlesDatabase? = null

        fun getAppDataBase(context: Context): ArticlesDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ArticlesDatabase::class.java,
                    "articlesDB"
                ).build()
            }.also {
                INSTANCE = it
            }
        }
    }
}

