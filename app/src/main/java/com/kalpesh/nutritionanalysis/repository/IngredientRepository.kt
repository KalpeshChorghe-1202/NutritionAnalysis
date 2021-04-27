package com.kalpesh.bpositive.mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.kalpesh.bpositive.mvvm.room.IngredientDatabse
import com.kalpesh.nutritionanalysis.model.IngredientsModel
import com.kalpesh.nutritionanalysis.room.IngredientEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class IngredientRepository {
    companion object {
        private var ingredientDatabse: IngredientDatabse? = null
        private var ingredientEnitity: LiveData<List<IngredientEntity>>? = null
        private fun initializeDB(context: Context): IngredientDatabse {
            return IngredientDatabse.getDataseClient(context)
        }

        fun insertData(context: Context, ncredientsModel: IngredientsModel) {
            ingredientDatabse = initializeDB(context)
            CoroutineScope(IO).launch {
                val obj = IngredientEntity(ncredientsModel.name, ncredientsModel.quantity,ncredientsModel.unit)
                ingredientDatabse!!.ingredientDoa().insertData(obj)
            }
        }

        fun getIngredientDetails(context: Context): LiveData<List<IngredientEntity>>? {
            ingredientDatabse = initializeDB(context)
            ingredientEnitity = ingredientDatabse!!.ingredientDoa().getIngredientDetails()
            return ingredientEnitity
        }



    }
}