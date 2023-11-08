package com.dalvarado.fizzbuzz.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dalvarado.fizzbuzz.model.SequenceRequest
import com.dalvarado.fizzbuzz.model.SequenceRequestRepository
import com.dalvarado.fizzbuzz.model.ui.SequenceFormUIState
import com.dalvarado.fizzbuzz.model.ui.TextFieldUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigInteger.ZERO

class SequenceRequestViewModel(
    private val sequenceRequestRepository: SequenceRequestRepository = SequenceRequestRepository.INSTANCE,
) : ViewModel() {
    private val _formState = MutableStateFlow(SequenceFormUIState())
    val formState: StateFlow<SequenceFormUIState> = _formState

    fun onFirstIntegerChanged(content: String) =
        _formState.apply {
            value =
                value.copy(
                    firstIntegerFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validateSequenceInteger(content),
                        ),
                )
        }

    fun onFirstWordChanged(content: String) =
        _formState.apply {
            value =
                value.copy(
                    firstWordFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validateSequenceWord(content),
                        ),
                )
        }

    fun onSecondIntegerChanged(content: String) =
        _formState.apply {
            value =
                value.copy(
                    secondIntegerFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validateSequenceInteger(content),
                        ),
                )
        }

    fun onSecondWordChanged(content: String) =
        _formState.apply {
            value =
                value.copy(
                    secondWordFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validateSequenceWord(content),
                        ),
                )
        }

    fun onSequenceLimitChanged(content: String) =
        _formState.apply {
            value =
                value.copy(
                    limitFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validateSequenceInteger(content),
                        ),
                )
        }

    fun onSubmitRequest() {
        if (_formState.value.isValid) {
            sequenceRequestRepository.setSequenceRequest(
                SequenceRequest(
                    firstInteger = _formState.value.firstIntegerFieldState.content.toBigInteger(),
                    firstWord = _formState.value.firstWordFieldState.content,
                    secondInteger = _formState.value.secondIntegerFieldState.content.toBigInteger(),
                    secondWord = _formState.value.secondWordFieldState.content,
                    sequenceLimit = _formState.value.limitFieldState.content.toBigInteger(),
                ),
            )
        }
    }

    private fun validateSequenceInteger(value: String): Boolean {
        return try {
            val validInteger = value.toBigInteger()
            validInteger > ZERO
        } catch (exception: NumberFormatException) {
            Log.e(null, "Error parsing new integer input:[$value]", exception)
            false
        }
    }

    private fun validateSequenceWord(value: String): Boolean {
        val validWord = value.matches(Regex("^\\w+$"))
        Log.d(null, "The word $value is${ if (validWord) " " else " not "}valid")
        return validWord
    }
}
