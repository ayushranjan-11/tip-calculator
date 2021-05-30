package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.calculateButton.setOnClickListener {

            if (binding.costOfServiceEditText.length()>0) {
                calculateTip()
            } else {
                Toast.makeText(this,"Cost of Service is empty",Toast.LENGTH_SHORT).show()
            }


        }
    }

    fun calculateTip() {
        val stringInTextField=binding.costOfServiceEditText.text.toString()
            val cost=stringInTextField.toDouble()

            val selectId=binding.serviceQuestionRadioGroup.checkedRadioButtonId

            val tipPercentage = when(selectId){
                R.id.option_twenty_percent->0.20
                R.id.option_eighteen_percent->0.18
                R.id.option_fifteen_percent->0.15
                else ->0.01
            }

            var tip=tipPercentage*cost

            //val roundUp = binding.roundTip.isChecked
            if(binding.roundTip.isChecked) {
                tip=kotlin.math.ceil(tip)
            }

            val formattedTip=NumberFormat.getCurrencyInstance().format(tip)

            binding.tipResult.text=getString(R.string.tip_amount,formattedTip)

    }
}