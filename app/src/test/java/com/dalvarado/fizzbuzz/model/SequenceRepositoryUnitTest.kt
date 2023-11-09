package com.dalvarado.fizzbuzz.model

import com.dalvarado.fizzbuzz.model.repository.SequenceRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SequenceRepositoryUnitTest {
    private val subject: SequenceRepository = SequenceRepository()

    @Test
    fun `Subject returns a sequence the size of sequence limit`() {
        val sequence = subject.getSequence(TEST_REQUEST)
        val count =
            sequence.fold(0) { acc, _ ->
                acc + 1
            }

        assertEquals(count, TEST_REQUEST.sequenceLimit)
    }

    @Test
    fun `Subject returns a sequence with first word in place of multiples of first integer`() {
        val sequence = subject.getSequence(TEST_REQUEST)
        checkMultiples(
            of = TEST_REQUEST.firstInteger,
            upTo = TEST_REQUEST.sequenceLimit,
            inSequence = sequence,
            contain = TEST_REQUEST.firstWord,
        )
    }

    @Test
    fun `Subject returns a sequence with second word in place of multiples of the second integer`() {
        val sequence = subject.getSequence(TEST_REQUEST)
        checkMultiples(
            of = TEST_REQUEST.secondInteger,
            upTo = TEST_REQUEST.sequenceLimit,
            inSequence = sequence,
            contain = TEST_REQUEST.secondWord,
        )
    }

    private fun checkMultiples(
        of: Int,
        upTo: Int,
        inSequence: Sequence<String>,
        contain: String,
    ) {
        var currentIndex = of - 1
        while (currentIndex < upTo) {
            assertTrue(inSequence.elementAt(currentIndex).contains(contain))
            currentIndex += of
        }
    }

    companion object {
        val TEST_REQUEST =
            SequenceRequest(
                firstInteger = 3,
                firstWord = "baba",
                secondInteger = 5,
                secondWord = "booey",
                sequenceLimit = 10000,
            )
    }
}
