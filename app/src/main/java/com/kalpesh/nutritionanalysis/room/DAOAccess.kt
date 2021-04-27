package com.kalpesh.bpositive.mvvm.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kalpesh.nutritionanalysis.room.IngredientEntity


@Dao
interface DAOAccess {

    @Insert
    fun insertData(vararg ingr: IngredientEntity)

    @Query("SELECT * FROM Ingredient ")
    fun getIngredientDetails(): LiveData<List<IngredientEntity>>



}
