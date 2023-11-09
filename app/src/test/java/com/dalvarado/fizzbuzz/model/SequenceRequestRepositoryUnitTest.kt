package com.dalvarado.fizzbuzz.model

import com.dalvarado.fizzbuzz.model.repository.SequenceRequestRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SequenceRequestRepositoryUnitTest {
    private val subject = SequenceRequestRepository.INSTANCE

    @Test
    fun `Subject is a singleton`() {
        assertTrue(subject === SequenceRequestRepository.INSTANCE)
    }

    @Test
    fun `Subject retains request instance`() {
        assertEquals(subject.getSequenceRequest(), SequenceRequest.EMPTY)
        subject.setSequenceRequest(TEST_REQUEST)
        assertEquals(subject.getSequenceRequest(), TEST_REQUEST)
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
