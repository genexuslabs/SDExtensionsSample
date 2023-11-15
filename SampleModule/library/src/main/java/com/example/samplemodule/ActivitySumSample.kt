package com.example.samplemodule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import com.example.samplemodule.ui.UIActivitySum

class ActivitySumSample : ComponentActivity() {

    private fun sumNumbers(num1 : Int, num2 : Int) : Int {
        return num1 + num2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIActivitySum(
                onClick = { number1, number2 ->
                    val number1Int = number1.toIntOrNull() ?: -1
                    val number2Int = number2.toIntOrNull() ?: -1
                    val sumResult = sumNumbers(number1Int, number2Int)

                    val resultIntent = Intent()
                    resultIntent.putExtra(KEY_RESULT, sumResult)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                },
                defaultNumber1 = intent.getIntExtra(KEY_NUMBER_1, -1),
                defaultNumber2 = intent.getIntExtra(KEY_NUMBER_2, -1)
            )
        }

        onBackPressedDispatcher.addCallback {
            val resultIntent = Intent()
            setResult(Activity.RESULT_CANCELED, resultIntent)
            finish()
        }
    }

    companion object {
        const val KEY_NUMBER_1 = "NUMBER_1"
        const val KEY_NUMBER_2 = "NUMBER_2"
        const val KEY_RESULT = "RESULT"
    }

}