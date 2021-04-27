package com.kalpesh.nutritionanalysis.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalpesh.bpositive.mvvm.repository.IngredientRepository
import com.kalpesh.nutritionanalysis.model.IngredientsModel
import com.kalpesh.nutritionanalysis.room.IngredientEntity

class NutritionVM : ViewModel() {

     var liveDataLogin: LiveData<List<IngredientEntity>>? = null

     fun insertData(context: Context, incredientsModel: IngredientsModel) {
         IngredientRepository.insertData(context, incredientsModel)
     }

     fun getDetails(context: Context): LiveData<List<IngredientEntity>>? {
         liveDataLogin = IngredientRepository.getIngredientDetails(context)
         return liveDataLogin
     }




}