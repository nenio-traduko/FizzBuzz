package com.dalvarado.fizzbuzz.model

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigInteger
import java.util.Random
import kotlin.math.abs

class SequenceRepositoryUnitTest {
    private val subject: SequenceRepository = SequenceRepository()

    @Test
    fun `Subject returns a sequence the size of sequence limit`() {
        val sequence = subject.getSequence(TEST_REQUEST)
        val count =
            sequence.fold(BigInteger.ZERO) { acc, _ ->
                acc + BigInteger.ONE
            }

        assertEquals(count, TEST_REQUEST.sequenceLimit)
    }

    @Test
    fun `Subject starts at one`() {
        val sequence = subject.getSequence(TEST_REQUEST)
        assertEquals(sequence.first(), MultiplesSequenceItem(value = BigInteger.ONE, sequenceRequest = TEST_REQUEST))
    }

    @Test
    fun `Subject increments each sequence element by one`() {
        val sequence = subject.getSequence(TEST_REQUEST)
        var previous: MultiplesSequenceItem? = null
        sequence.drop(abs(Random().nextInt(9999))).take(6).forEach { item ->
            previous?.run {
                assertEquals(this + BigInteger.ONE, item)
            }
            previous = item
        }
    }

    companion object {
        val TEST_REQUEST =
            SequenceRequest(
                firstInteger = BigInteger("3"),
                firstWord = "baba",
                secondInteger = BigInteger("5"),
                secondWord = "booey",
                sequenceLimit = BigInteger("1000000"),
            )
    }
}
