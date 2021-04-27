package com.kalpesh.nutritionanalysis.view

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.kalpesh.nutritionanalysis.R
import com.kalpesh.nutritionanalysis.adapter.NutritionAdapter
import com.kalpesh.nutritionanalysis.databinding.ActivityRecyclerViewBinding
import com.kalpesh.nutritionanalysis.model.IngredientsModel
import com.kalpesh.nutritionanalysis.viewmodel.NutritionVM
import kotlinx.android.synthetic.main.activity_recycler_view.*

class NutritionListActivity : AppCompatActivity() {
    private lateinit var adapter: NutritionAdapter
    lateinit var incredientsList: ArrayList<IngredientsModel>
    private var mVM: NutritionVM? = null
    private var binding: ActivityRecyclerViewBinding? = null
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        mVM = ViewModelProviders.of(this).get(NutritionVM::class.java)
        binding?.lifecycleOwner = this
        binding?.viewModel = mVM
        context = this@NutritionListActivity
        initDb()
        setupUI()
    }

    private fun initDb() {
        mVM?.getDetails(context)!!.observe(this, Observer {
            it?.let {
                incredientsList = ArrayList()
                    it.forEach { it ->
                        val incredientsModel = IngredientsModel()
                        incredientsModel.name = it.name
                        incredientsModel.quantity = it.quantity
                        incredientsModel.unit = it.unit
                        incredientsList.add(incredientsModel)
                    }
                renderList(incredientsList)
            } ?: run {
                Toast.makeText(context, "Record Not found", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            NutritionAdapter(
                arrayListOf()
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun renderList(list: List<IngredientsModel>) {
        adapter.addData(list)
        adapter.notifyDataSetChanged()
    }
}