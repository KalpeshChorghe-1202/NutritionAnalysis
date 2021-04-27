package com.kalpesh.nutritionanalysis.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalpesh.nutritionanalysis.R
import com.kalpesh.nutritionanalysis.model.IngredientsModel
import kotlinx.android.synthetic.main.item_layout.view.*

class NutritionAdapter(
    private val list: ArrayList<IngredientsModel>
) : RecyclerView.Adapter<NutritionAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(obj: IngredientsModel) {
            itemView.tvName.text = "Food Name: ".plus(obj.name)
            itemView.tvQuanitity.text = "Quanitity: ".plus(obj.quantity)
            itemView.tvUnit.text = "Unit: ".plus(obj.unit)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(list[position])

    fun addData(list: List<IngredientsModel>) {
        this.list.addAll(list)
    }


}