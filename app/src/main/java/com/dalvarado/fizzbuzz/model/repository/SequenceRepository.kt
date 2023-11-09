package com.dalvarado.fizzbuzz.model.repository

import com.dalvarado.fizzbuzz.model.SequenceRequest
import com.dalvarado.fizzbuzz.model.extensions.isDivisible

class SequenceRepository {
    fun getSequence(request: SequenceRequest): Sequence<String> =
        generateSequence(
            1,
        ) { previousItem ->
            if (previousItem < request.sequenceLimit) {
                previousItem + 1
            } else {
                null
            }
        }.map { number ->
            val isDivisibleByFirstFactor = number.isDivisible(request.firstInteger)
            val isDivisibleBySecondFactor = number.isDivisible(request.secondInteger)

            if (!isDivisibleByFirstFactor && !isDivisibleBySecondFactor) {
                number.toString()
            } else {
                var stringRepresentation = ""
                if (isDivisibleByFirstFactor) {
                    stringRepresentation += request.firstWord
                }

                if (isDivisibleBySecondFactor) {
                    stringRepresentation += request.secondWord
                }

                stringRepresentation
            }
        }
}
