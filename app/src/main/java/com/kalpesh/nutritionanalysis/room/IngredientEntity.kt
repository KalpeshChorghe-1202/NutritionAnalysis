package com.kalpesh.nutritionanalysis.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Ingredient")
data class IngredientEntity(

    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "quantity") var quantity: String,
    @ColumnInfo(name = "unit") var unit: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}