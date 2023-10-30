package com.dalvarado.fizzbuzz.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainView() {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("FizzBuzz") }
      )
    }
  ) { padding ->
    Column(
      Modifier.padding(padding),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      Spacer(modifier = Modifier.height(Min))
      IntegerField(label = "Integer One")
      WordField(label = "Word One")
      IntegerField(label = "Integer Two")
      WordField(label = "Word Two")
      IntegerField(label = "Limit")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
  MaterialTheme {
    MainView()
  }
}