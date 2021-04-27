package com.kalpesh.nutritionanalysis.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.kalpesh.nutritionanalysis.R
import com.kalpesh.nutritionanalysis.databinding.ActivityMainBinding
import com.kalpesh.nutritionanalysis.model.IngredientsModel
import com.kalpesh.nutritionanalysis.viewmodel.NutritionVM
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mVM: NutritionVM? = null
    private val mBinding: ActivityMainBinding? = null
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mVM = ViewModelProviders.of(this).get(NutritionVM::class.java)
        mBinding?.lifecycleOwner = this
        mBinding?.viewModel = mVM
        context = this@MainActivity
        enableButton()
    }

    private fun enableButton() {

        ed_name.addTextChangedListener(textWatcher)
        ed_quantity.addTextChangedListener(textWatcher)
        ed_unit.addTextChangedListener(textWatcher)

        btn_analyse.setOnClickListener {

            val incredientsModel = IngredientsModel()
            incredientsModel.name = ed_name.text.toString()
            incredientsModel.quantity = ed_quantity.text.toString()
            incredientsModel.unit = ed_unit.text.toString()
            mVM?.insertData(context, incredientsModel)
            Toast.makeText(context, "Record inserted sucessfully", Toast.LENGTH_LONG).show()
            clearFields()
        }
        btn_summary.setOnClickListener {
            startActivity(Intent(this@MainActivity, NutritionListActivity::class.java))
        }
    }


    private fun clearFields() {
        ed_name.setText("")
        ed_quantity.setText("")
        ed_unit.setText("")

    }

    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            if (!TextUtils.isEmpty(ed_name.text.toString().trim())
                || !TextUtils.isEmpty(ed_quantity.text.toString().trim())
                || !TextUtils.isEmpty(ed_unit.text.toString().trim())
            ) {
                val firtValue = if (TextUtils.isEmpty(
                        ed_name.text.toString().trim()
                    )
                ) 0 else 1
                val secondValue = if (TextUtils.isEmpty(
                        ed_quantity.text.toString().trim()
                    )
                ) 0 else 1
                val thirdValue = if (TextUtils.isEmpty(
                        ed_unit.text.toString().trim()
                    )
                ) 0 else 1

                if (firtValue > 0 && secondValue > 0 && thirdValue > 0) {
                    btn_analyse.isEnabled = true
                    btn_analyse.setBackgroundResource(R.color.colorAccent)
                } else {
                    btn_analyse.isEnabled = false
                    btn_analyse.setBackgroundResource(R.color.colorWhite)
                }
            } else {
                btn_analyse.isEnabled = false
            }
        }

        override fun afterTextChanged(editable: Editable) {}
    }

}



