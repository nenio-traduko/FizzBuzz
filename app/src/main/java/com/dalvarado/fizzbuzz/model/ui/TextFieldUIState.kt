package com.dalvarado.fizzbuzz.model.ui

data class TextFieldUIState(
    val content: String = "",
    val isValid: Boolean? = null,
    val errorMessage: Int? = null,
)
