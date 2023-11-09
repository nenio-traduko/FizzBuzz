package com.dalvarado.fizzbuzz.viewmodel

import com.dalvarado.fizzbuzz.model.SequenceRequest
import com.dalvarado.fizzbuzz.model.repository.SequenceRequestRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SequenceViewModelUnitTest {
    private lateinit var subject: SequenceViewModel

    @Before
    fun setUp() {
        SequenceRequestRepository.INSTANCE.setSequenceRequest(TEST_REQUEST)
        subject =
            SequenceViewModel(
                sequenceRequestRepository = SequenceRequestRepository.INSTANCE,
            )
    }

    @Test
    fun `Subjects sequenceSize property is the same as the specified sequence limit`() {
        assertEquals(subject.sequenceSize, TEST_REQUEST.sequenceLimit)
    }

    @Test
    fun `Subjects sequenceSize property presents is the real size of the sequence`() {
        assertEquals(subject.sequenceSize, subject.sequence.count())
    }

    companion object {
        val TEST_REQUEST =
            SequenceRequest(
                firstInteger = 3,
                firstWord = "baba",
                secondInteger = 5,
                secondWord = "booey",
                sequenceLimit = 1000,
            )
    }
}
