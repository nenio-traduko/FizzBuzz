package com.dalvarado.fizzbuzz.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dalvarado.fizzbuzz.R
import com.dalvarado.fizzbuzz.viewmodel.SequenceRequestViewModel

@Composable
fun SequenceRequestView(
    viewModel: SequenceRequestViewModel = viewModel(),
    onViewSequence: () -> Unit = {},
) {
    val uiState = viewModel.formState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FizzBuzz") },
            )
        },
    ) { padding ->
        Column(
            Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            IntegerField(
                label = stringResource(id = R.string.first_integer_input_label),
                state = uiState.value.firstIntegerFieldState,
                onValueChange = viewModel::onFirstIntegerChanged,
            )
            WordField(
                label = stringResource(id = R.string.first_word_input_label),
                state = uiState.value.firstWordFieldState,
                onValueChange = viewModel::onFirstWordChanged,
            )
            IntegerField(
                label = stringResource(id = R.string.second_integer_input_label),
                state = uiState.value.secondIntegerFieldState,
                onValueChange = viewModel::onSecondIntegerChanged,
            )
            WordField(
                label = stringResource(id = R.string.second_word_input_label),
                state = uiState.value.secondWordFieldState,
                onValueChange = viewModel::onSecondWordChanged,
            )
            IntegerField(
                label = stringResource(id = R.string.limit_input_label),
                state = uiState.value.limitFieldState,
                onValueChange = viewModel::onSequenceLimitChanged,
            )
            Button(
                modifier =
                    Modifier
                        .fillMaxWidth(fraction = 0.6f)
                        .align(Alignment.CenterHorizontally),
                onClick = {
                    viewModel.onSubmitRequest()
                    onViewSequence()
                },
                enabled = uiState.value.isValid,
            ) {
                Text(text = stringResource(id = R.string.submit_button_label))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SequenceRequestViewPreview() {
    MaterialTheme {
        SequenceRequestView()
    }
}
