package com.dalvarado.fizzbuzz.model

data class SequenceRequest(
    val firstInteger: Int,
    val firstWord: String,
    val secondInteger: Int,
    val secondWord: String,
    val sequenceLimit: Int,
) {
    companion object {
        val EMPTY =
            SequenceRequest(
                firstInteger = 0,
                firstWord = "",
                secondInteger = 0,
                secondWord = "",
                sequenceLimit = 0,
            )
    }
}
