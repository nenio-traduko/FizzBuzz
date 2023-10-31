package com.dalvarado.fizzbuzz.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IntegerField(label: String, min: UInt = 1u, max: UInt = UInt.MAX_VALUE) {
  val NOT_IN_RANGE_ERROR_MESSAGE = "The given integer is not in the accepted range."
  val NOT_A_VALID_INTEGER_ERROR_MESSAGE = "The given input is not a valid integer or is too large."
  var value by rememberSaveable {  mutableStateOf("") }
  var isValid by rememberSaveable { mutableStateOf(true) }
  var errorMessage by rememberSaveable {
    mutableStateOf(NOT_IN_RANGE_ERROR_MESSAGE)
  }
  Column(
    Modifier
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    TextField(
      value = value,
      onValueChange = { newValue ->
        try {
          value = newValue

          if (newValue.isNotEmpty()) {
            val newIntValue = newValue.toUInt()

            Log.d(null, "$value was successfully parsed as an Int type.")

            errorMessage = NOT_IN_RANGE_ERROR_MESSAGE
            isValid = newIntValue in min..max

            Log.d(null, "$value was${if (isValid) " " else " not "}in range.")
          } else {
            isValid = true
          }
        } catch (error: NumberFormatException) {
          Log.e(null, "Error parsing new IntegerField value.", error)
          errorMessage = NOT_A_VALID_INTEGER_ERROR_MESSAGE
          isValid = false
        }
      },
      label = { Text(text = label) },
      isError = !isValid,
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    if (!isValid) {
      Text(text = errorMessage, color = Color.Red)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun IntegerFieldPreview() {
  MaterialTheme {
    IntegerField(label = "Test Number")
  }
}