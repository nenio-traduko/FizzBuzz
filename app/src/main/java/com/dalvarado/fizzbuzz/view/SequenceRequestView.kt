package com.dalvarado.fizzbuzz.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
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
  viewModel: SequenceRequestViewModel = viewModel()
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("FizzBuzz") }
      )
    }
  ) { padding ->
    Column(
      Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      IntegerField(
        label = stringResource(id = R.string.first_integer_input_label),
        value = viewModel.firstInteger,
        onValueChange = viewModel::onFirstIntegerChanged,
        isError = !viewModel.isFirstIntegerValid
      )
      WordField(
        label = stringResource(id = R.string.first_word_input_label),
        value = viewModel.firstWord,
        onValueChange = viewModel::onFirstWordChanged,
        isError = !viewModel.isFirstWordValid
      )
      IntegerField(
        label = stringResource(id = R.string.second_integer_input_label),
        value = viewModel.secondInteger,
        onValueChange = viewModel::onSecondIntegerChanged,
        isError = !viewModel.isSecondIntegerValid
      )
      WordField(
        label = stringResource(id = R.string.second_word_input_label),
        value = viewModel.secondWord,
        onValueChange = viewModel::onSecondWordChanged,
        isError = !viewModel.isSecondWordValid
      )
      IntegerField(
        label = stringResource(id = R.string.limit_input_label),
        value = viewModel.sequenceLimit,
        onValueChange = viewModel::onSequenceLimitChanged,
        isError = !viewModel.isSequenceLimitValid
      )
      Button(
        modifier = Modifier
          .fillMaxWidth(fraction = 0.6f)
          .align(Alignment.CenterHorizontally),
        onClick = viewModel::onSubmitRequest,
        enabled = viewModel.isRequestValid
      ) {
        Text(text = "Submit")
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