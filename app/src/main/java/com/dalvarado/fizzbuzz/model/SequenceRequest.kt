package com.dalvarado.fizzbuzz.model

import java.math.BigInteger

data class SequenceRequest(
    val firstInteger: BigInteger,
    val firstWord: String,
    val secondInteger: BigInteger,
    val secondWord: String,
    val sequenceLimit: BigInteger,
) {
    companion object {
        val EMPTY =
            SequenceRequest(
                firstInteger = BigInteger.ZERO,
                firstWord = "",
                secondInteger = BigInteger.ZERO,
                secondWord = "",
                sequenceLimit = BigInteger.ZERO,
            )
    }
}
