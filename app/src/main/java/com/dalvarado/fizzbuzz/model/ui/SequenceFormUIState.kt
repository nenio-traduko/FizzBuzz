package com.dalvarado.fizzbuzz.model.ui

data class SequenceFormUIState(
    val firstIntegerFieldState: TextFieldUIState = TextFieldUIState(),
    val firstWordFieldState: TextFieldUIState = TextFieldUIState(),
    val secondIntegerFieldState: TextFieldUIState = TextFieldUIState(),
    val secondWordFieldState: TextFieldUIState = TextFieldUIState(),
    val limitFieldState: TextFieldUIState = TextFieldUIState(),
) {
    val isValid: Boolean
        get() {
            return firstIntegerFieldState.isValid ?: false &&
                firstWordFieldState.isValid ?: false &&
                secondIntegerFieldState.isValid ?: false &&
                secondWordFieldState.isValid ?: false &&
                limitFieldState.isValid ?: false
        }
}
