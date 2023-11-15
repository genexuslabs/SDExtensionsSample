package com.example.samplemodule

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.genexusmodule.R

class ActivitySumSample : AppCompatActivity() {

    private fun sumNumbers(num1 : Int, num2 : Int) : Int {
        return num1 + num2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sum_sample)

        val number1 = intent.extras?.getInt(KEY_NUMBER_1) ?: -1
        val number2 = intent.extras?.getInt(KEY_NUMBER_2) ?: -1

        val txtNumber1 = findViewById<TextView>(R.id.txtNumber1)
        val txtNumber2 = findViewById<TextView>(R.id.txtNumber2)
        val buttonSum = findViewById<Button>(R.id.btnSum)

        txtNumber1.text = number1.toString()
        txtNumber2.text = number2.toString()

        buttonSum.setOnClickListener {
            val sumResult = sumNumbers(number1, number2)
            val resultIntent = Intent()
            resultIntent.putExtra(KEY_RESULT, sumResult)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    companion object {
        val KEY_NUMBER_1 = "NUMBER_1"
        val KEY_NUMBER_2 = "NUMBER_2"
        val KEY_RESULT = "RESULT"
    }

}