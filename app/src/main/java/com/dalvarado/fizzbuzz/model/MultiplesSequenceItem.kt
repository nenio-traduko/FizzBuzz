package com.dalvarado.fizzbuzz.model

import com.dalvarado.fizzbuzz.model.extensions.isDivisible
import java.math.BigInteger

class MultiplesSequenceItem(
  private val value: BigInteger,
  private val sequenceRequest: SequenceRequest
) {
  operator fun plus(rhs: BigInteger): MultiplesSequenceItem = MultiplesSequenceItem(
    value = value + rhs,
    sequenceRequest = sequenceRequest
  )

  operator fun compareTo(rhs: BigInteger): Int = value.compareTo(rhs)
  override fun toString(): String {
    val isDivisibleByFirstFactor = value.isDivisible(sequenceRequest.firstInteger)
    val isDivisibleBySecondFactor = value.isDivisible(sequenceRequest.secondInteger)

    return if (!isDivisibleByFirstFactor && !isDivisibleBySecondFactor) {
      value.toString()
    } else {
      var stringRepresentation = ""
      if (isDivisibleByFirstFactor) {
        stringRepresentation += sequenceRequest.firstWord
      }

      if (isDivisibleBySecondFactor) {
        stringRepresentation += sequenceRequest.secondWord
      }

      stringRepresentation
    }
  }

  override operator fun equals(other: Any?): Boolean {
    if (other is MultiplesSequenceItem) {
      return value == other.value && sequenceRequest == other.sequenceRequest
    }

    return false
  }

  override fun hashCode(): Int {
    var result = value.hashCode()
    result = 31 * result + sequenceRequest.hashCode()
    return result
  }
}