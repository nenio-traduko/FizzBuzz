package com.dalvarado.fizzbuzz.viewmodel

import androidx.lifecycle.ViewModel
import com.dalvarado.fizzbuzz.R
import com.dalvarado.fizzbuzz.model.SequenceRequest
import com.dalvarado.fizzbuzz.model.repository.SequenceRequestRepository
import com.dalvarado.fizzbuzz.model.repository.api.SequenceRequestStore
import com.dalvarado.fizzbuzz.model.ui.SequenceFormUIState
import com.dalvarado.fizzbuzz.model.ui.TextFieldUIState
import com.dalvarado.fizzbuzz.model.utils.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigInteger

class SequenceRequestViewModel(
    private val sequenceRequestRepository: SequenceRequestStore = SequenceRequestRepository.INSTANCE,
    private val log: Logger = Logger(),
) : ViewModel() {
    private val _formState = MutableStateFlow(SequenceFormUIState())
    val formState: StateFlow<SequenceFormUIState> = _formState

    fun onFirstIntegerChanged(content: String) =
        _formState.apply {
            val validationResult = validateIntegerField(content)
            value =
                value.copy(
                    firstIntegerFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validationResult.first,
                            errorMessage = validationResult.second,
                        ),
                )
        }

    fun onFirstWordChanged(content: String) =
        _formState.apply {
            val validationResult = validateWordField(content)
            value =
                value.copy(
                    firstWordFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validationResult.first,
                            errorMessage = validationResult.second,
                        ),
                )
        }

    fun onSecondIntegerChanged(content: String) =
        _formState.apply {
            val validationResult = validateIntegerField(content)
            value =
                value.copy(
                    secondIntegerFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validationResult.first,
                            errorMessage = validationResult.second,
                        ),
                )
        }

    fun onSecondWordChanged(content: String) =
        _formState.apply {
            val validationResult = validateWordField(content)
            value =
                value.copy(
                    secondWordFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validationResult.first,
                            errorMessage = validationResult.second,
                        ),
                )
        }

    fun onSequenceLimitChanged(content: String) =
        _formState.apply {
            val validationResult = validateIntegerField(content)
            value =
                value.copy(
                    limitFieldState =
                        TextFieldUIState(
                            content = content,
                            isValid = validationResult.first,
                            errorMessage = validationResult.second,
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

    private fun validateIntegerField(input: String): Pair<Boolean, Int?> {
        return if (input.isBlank()) {
            Pair(false, R.string.field_empty_error_message)
        } else {
            parseAndValidateInteger(input)
        }
    }

    private fun parseAndValidateInteger(value: String): Pair<Boolean, Int?> {
        try {
            val validInteger = value.toBigInteger()
            log.info("FizzBuzz", "$value was parsed as an integer.")

            if (MAX_INTEGER < validInteger) {
                log.debug("FizzBuzz", "The number is not valid because it is too large.")
                return Pair(false, R.string.number_too_large_error_message)
            }

            if (validInteger <= BigInteger.ZERO) {
                log.debug("FizzBuzz", "The number is not valid because it is not positive.")
                return Pair(false, R.string.non_positive_number_error_message)
            }

            return Pair(true, null)
        } catch (exception: NumberFormatException) {
            log.error("FizzBuzz", "Error parsing input:[$value] as an integer.")
            return Pair(false, R.string.not_a_number_error_message)
        }
    }

    private fun validateWordField(input: String): Pair<Boolean, Int?> {
        return if (input.isBlank()) {
            Pair(false, R.string.field_empty_error_message)
        } else {
            val validWord = input.matches(Regex("^\\w+$"))
            log.debug("FizzBuzz", "The word $input is${ if (validWord) " " else " not "}valid")
            Pair(validWord, if (validWord) null else R.string.invalid_word_error_message)
        }
    }

    companion object {
        private val MAX_INTEGER = BigInteger(Int.MAX_VALUE.toString())
    }
}
