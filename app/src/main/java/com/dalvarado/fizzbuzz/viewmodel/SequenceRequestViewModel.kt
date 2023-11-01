package com.dalvarado.fizzbuzz.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dalvarado.fizzbuzz.model.SequenceRequest
import com.dalvarado.fizzbuzz.model.SequenceRequestRepository
import java.math.BigInteger.ZERO

class SequenceRequestViewModel(
  private val sequenceRequestRepository: SequenceRequestRepository = SequenceRequestRepository()
): ViewModel() {
  var firstInteger by mutableStateOf("")
    private set

  var isFirstIntegerValid by mutableStateOf(true)
    private set

  var firstWord by mutableStateOf("")
    private set

  var isFirstWordValid by mutableStateOf(true)
    private set

  var secondInteger by mutableStateOf("")
    private set

  var isSecondIntegerValid by mutableStateOf(true)

  var secondWord by mutableStateOf("")
    private set

  var isSecondWordValid by mutableStateOf(true)
    private set

  var sequenceLimit by mutableStateOf("")

  var isSequenceLimitValid by mutableStateOf(true)
    private set

  val isRequestValid: Boolean
    get() {
      return validateSequenceInteger(firstInteger) && validateSequenceWord(firstWord) &&
          validateSequenceInteger(secondInteger) && validateSequenceWord(secondWord) &&
          validateSequenceInteger(sequenceLimit)
    }

  fun onFirstIntegerChanged(value: String) {
    isFirstIntegerValid = validateSequenceInteger(value)
    firstInteger = value
  }

  fun onFirstWordChanged(value: String) {
    isFirstWordValid = validateSequenceWord(value)
    firstWord = value
  }

  fun onSecondIntegerChanged(value: String) {
    isSecondIntegerValid = validateSequenceInteger(value)
    secondInteger = value
  }

  fun onSecondWordChanged(value: String) {
    isSecondWordValid = validateSequenceWord(value)
    secondWord = value
  }

  fun onSequenceLimitChanged(value: String) {
    isSequenceLimitValid = validateSequenceInteger(value)
    sequenceLimit = value
  }

  fun onSubmitRequest() {
    if (isRequestValid) {
      sequenceRequestRepository.addSequenceRequest(
        SequenceRequest(
          firstInteger = firstInteger.toBigInteger(),
          firstWord = firstWord,
          secondInteger = secondInteger.toBigInteger(),
          secondWord = secondWord,
          sequenceLimit = sequenceLimit.toBigInteger()
        )
      )
    }
  }
  private fun validateSequenceInteger(value: String): Boolean {
    return try {
      val validInteger = value.toBigInteger()
      validInteger > ZERO
    } catch(exception: NumberFormatException) {
      Log.e(null, "Error parsing new integer input:[$value]", exception)
      false
    }
  }

  private fun validateSequenceWord(value: String): Boolean {
    val validWord = value.matches(Regex("^\\w+$"))
    Log.d(null, "The word ${value} is${ if (validWord) " " else " not "}valid")
    return validWord
  }
}