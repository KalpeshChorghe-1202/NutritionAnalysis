package com.kalpesh.bpositive.mvvm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kalpesh.nutritionanalysis.room.IngredientEntity

@Database(entities = [IngredientEntity::class],version = 1,exportSchema = false)
abstract class IngredientDatabse :RoomDatabase(){
 abstract fun ingredientDoa() :DAOAccess
    companion object {

        @Volatile
        private var INSTANCE: IngredientDatabse? = null

        fun getDataseClient(context: Context) : IngredientDatabse {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                        .databaseBuilder(context, IngredientDatabse::class.java, "INGREDIENT_DATABASE")
                        .fallbackToDestructiveMigration()
                        .build()

                return INSTANCE!!

            }
        }

    }
}