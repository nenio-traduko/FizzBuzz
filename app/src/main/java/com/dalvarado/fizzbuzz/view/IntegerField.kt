package com.dalvarado.fizzbuzz.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.dalvarado.fizzbuzz.R
import com.dalvarado.fizzbuzz.model.ui.TextFieldUIState

@Composable
fun IntegerField(
    label: String,
    state: TextFieldUIState,
    onValueChange: (String) -> Unit = {},
) {
    val isError = if (state.isValid == null) false else !state.isValid
    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = state.content,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            isError = isError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        if (isError) {
            Text(text = stringResource(id = R.string.invalid_integer_error_message), color = Color.Red)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntegerFieldPreview() {
    MaterialTheme {
        IntegerField(label = "Test Number", state = TextFieldUIState("13"))
    }
}
