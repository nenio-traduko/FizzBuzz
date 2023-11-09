package com.dalvarado.fizzbuzz.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dalvarado.fizzbuzz.viewmodel.SequenceViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SequenceView(
    viewModel: SequenceViewModel = viewModel(),
    onBack: (() -> Unit)? = null,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sequence") },
                navigationIcon = {
                    onBack?.let {
                        IconButton(onClick = { it() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colors.onPrimary)
                        }
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            items(viewModel.sequenceSize) { index ->
                ListItem(
                    text = {
                        Text(
                            text = viewModel.sequence.elementAt(index),
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                        )
                    },
                )
                Divider()
            }
        }
    }
}

@Preview
@Composable
fun SequenceViewPreview() {
    MaterialTheme {
        SequenceView()
    }
}
