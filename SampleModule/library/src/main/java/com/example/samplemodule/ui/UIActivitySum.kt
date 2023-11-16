package com.example.samplemodule.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.genexusmodule.R

@Composable
fun UIActivitySum(
    onClick : (number1 : String, number2 : String) -> Unit,
    defaultNumber1 : Int,
    defaultNumber2 : Int
) {

    val (number1, setNumber1) = remember { mutableStateOf(defaultNumber1.toString()) }
    val (number2, setNumber2) = remember { mutableStateOf(defaultNumber2.toString()) }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField (
            value = number1,
            onValueChange = { setNumber1(it) },
            label = { Text(stringResource(id = R.string.ui_sum_number_1_label)) }
        )
        TextField (
            value = number2,
            onValueChange = { setNumber2(it) },
            label = { Text(stringResource(id = R.string.ui_sum_number_2_label)) }
        )
        Button(
            onClick = {
                onClick.invoke(number1, number2)
            }) {
            Text(stringResource(id = R.string.ui_sum_button_label))
        }

    }
}
