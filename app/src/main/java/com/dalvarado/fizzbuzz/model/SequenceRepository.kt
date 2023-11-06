package com.dalvarado.fizzbuzz.model

import java.math.BigInteger

class SequenceRepository {
    fun getSequence(request: SequenceRequest): Sequence<MultiplesSequenceItem> =
        generateSequence(
            MultiplesSequenceItem(value = BigInteger.ONE, sequenceRequest = request),
        ) { previousItem ->
            if (previousItem < request.sequenceLimit) {
                previousItem + BigInteger.ONE
            } else {
                null
            }
        }
}
