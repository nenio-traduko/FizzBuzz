package com.dalvarado.fizzbuzz.viewmodel

import androidx.lifecycle.ViewModel
import com.dalvarado.fizzbuzz.model.SequenceRequest
import com.dalvarado.fizzbuzz.model.repository.SequenceRequestRepository
import com.dalvarado.fizzbuzz.model.repository.api.SequenceRequestStore
import com.dalvarado.fizzbuzz.model.ui.SequenceFormUIState
import com.dalvarado.fizzbuzz.model.ui.TextFieldUIState
import com.dalvarado.fizzbuzz.model.utils.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SequenceRequestViewModel(
    private val sequenceRequestRepository: SequenceRequestStore = SequenceRequestRepository.INSTANCE,
    private val log: Logger = Logger(),
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
                    firstInteger = _formState.value.firstIntegerFieldState.content.toInt(),
                    firstWord = _formState.value.firstWordFieldState.content,
                    secondInteger = _formState.value.secondIntegerFieldState.content.toInt(),
                    secondWord = _formState.value.secondWordFieldState.content,
                    sequenceLimit = _formState.value.limitFieldState.content.toInt(),
                ),
            )
        }
    }

    private fun validateSequenceInteger(value: String): Boolean {
        return try {
            val validInteger = value.toInt()
            log.info("FizzBuzz", "$value was parsed as an integer")
            validInteger > 0
        } catch (exception: NumberFormatException) {
            log.error("FizzBuzz", "Error parsing new integer input:[$value]")
            false
        }
    }

    private fun validateSequenceWord(value: String): Boolean {
        val validWord = value.matches(Regex("^\\w+$"))
        log.debug("FizzBuzz", "The word $value is${ if (validWord) " " else " not "}valid")
        return validWord
    }
}
