package com.example.samplemodule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.samplemodule.ui.UIActivitySum

class ActivityAddNumbers : ComponentActivity() {

    private fun sumNumbers(num1 : Int, num2 : Int) : Int {
        return num1 + num2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIActivitySum(
                onClick = { number1, number2 ->
                    val number1Int = number1.toIntOrNull() ?: DEFAULT_NUMBER_VALUES
                    val number2Int = number2.toIntOrNull() ?: DEFAULT_NUMBER_VALUES
                    val sumResult = sumNumbers(number1Int, number2Int)

                    val resultIntent = Intent().apply { putExtra(KEY_RESULT, sumResult) }
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                },
                defaultNumber1 = intent.getIntExtra(KEY_NUMBER_1, DEFAULT_NUMBER_VALUES),
                defaultNumber2 = intent.getIntExtra(KEY_NUMBER_2, DEFAULT_NUMBER_VALUES)
            )
        }

    }

    companion object {
        const val DEFAULT_NUMBER_VALUES = 0
        const val KEY_NUMBER_1 = "NUMBER_1"
        const val KEY_NUMBER_2 = "NUMBER_2"
        const val KEY_RESULT = "RESULT"
    }

}
