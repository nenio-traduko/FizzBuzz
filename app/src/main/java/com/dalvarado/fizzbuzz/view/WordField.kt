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
fun WordField(label: String) {
  var value by rememberSaveable {  mutableStateOf("") }
  var isValid by rememberSaveable { mutableStateOf(true) }

  Column(
    Modifier
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    TextField(
      value = value,
      onValueChange = { newValue ->
        value = newValue

        if (value.isNotEmpty()) {
          isValid = isValid(newValue)
        } else {
          isValid = true
        }
      },
      label = { Text(text = label) },
      isError = !isValid,
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )

    if (!isValid) {
      Text(text = "The given word contains invalid characters.", color = Color.Red)
    }
  }
}

fun isValid(word: String): Boolean {
  Log.d(null, "Validating word:[${word}]")
  val validWord = word.matches(Regex("^\\w+$"))
  Log.d(null, "The word ${word} is${ if (validWord) " " else " not "}valid")
  return validWord
}

@Preview(showBackground = true)
@Composable
fun WordFieldPreview() {
  MaterialTheme {
    WordField(label = "Test Word")
  }
}