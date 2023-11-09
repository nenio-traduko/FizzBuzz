package com.dalvarado.fizzbuzz.viewmodel

import com.dalvarado.fizzbuzz.model.SequenceRequest
import com.dalvarado.fizzbuzz.model.repository.api.SequenceRequestStore
import com.dalvarado.fizzbuzz.model.ui.SequenceFormUIState
import com.dalvarado.fizzbuzz.model.ui.TextFieldUIState
import com.dalvarado.fizzbuzz.model.utils.Logger
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions

class SequenceRequestViewModelUnitTest {
    private lateinit var loggerMock: Logger
    private lateinit var requestStoreMock: SequenceRequestStore
    private lateinit var subject: SequenceRequestViewModel

    @Before
    fun setUp() {
        loggerMock = mock(Logger::class.java)
        requestStoreMock = mock(SequenceRequestStore::class.java)

        subject =
            SequenceRequestViewModel(
                sequenceRequestRepository = requestStoreMock,
                log = loggerMock,
            )
    }

    @Test
    fun `Subject returns an empty UI state before receiving input`() =
        runTest {
            val uiState = subject.formState.first()
            assertEquals(uiState, SequenceFormUIState())
        }

    @Test
    fun `Subject returns expected state for invalid first integer input`() =
        runTest {
            val invalidInput = "12?3<."
            subject.onFirstIntegerChanged(invalidInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.firstIntegerFieldState, TextFieldUIState(content = invalidInput, isValid = false))
        }

    @Test
    fun `Subject returns expected state for valid first integer input`() =
        runTest {
            val validInput = "112233"
            subject.onFirstIntegerChanged(validInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.firstIntegerFieldState, TextFieldUIState(content = validInput, isValid = true))
        }

    @Test
    fun `Subject returns expected state for invalid second integer input`() =
        runTest {
            val invalidInput = "12?3<."
            subject.onSecondIntegerChanged(invalidInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.secondIntegerFieldState, TextFieldUIState(content = invalidInput, isValid = false))
        }

    @Test
    fun `Subject returns expected state for valid second integer input`() =
        runTest {
            val validInput = "112233"
            subject.onSecondIntegerChanged(validInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.secondIntegerFieldState, TextFieldUIState(content = validInput, isValid = true))
        }

    @Test
    fun `Subject returns expected state for invalid limit input`() =
        runTest {
            val invalidInput = "12?3<."
            subject.onSequenceLimitChanged(invalidInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.limitFieldState, TextFieldUIState(content = invalidInput, isValid = false))
        }

    @Test
    fun `Subject returns expected state for valid limit input`() =
        runTest {
            val validInput = "112233"
            subject.onSequenceLimitChanged(validInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.limitFieldState, TextFieldUIState(content = validInput, isValid = true))
        }

    @Test
    fun `Subject returns expected state for invalid first word input`() =
        runTest {
            val invalidInput = " .-%"
            subject.onFirstWordChanged(invalidInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.firstWordFieldState, TextFieldUIState(content = invalidInput, isValid = false))
        }

    @Test
    fun `Subject returns expected state for valid first word input`() =
        runTest {
            val validInput = "foobar"
            subject.onFirstWordChanged(validInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.firstWordFieldState, TextFieldUIState(content = validInput, isValid = true))
        }

    @Test
    fun `Subject returns expected state for invalid second word input`() =
        runTest {
            val invalidInput = " .-%"
            subject.onSecondWordChanged(invalidInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.secondWordFieldState, TextFieldUIState(content = invalidInput, isValid = false))
        }

    @Test
    fun `Subject returns expected state for valid second word input`() =
        runTest {
            val validInput = "foobar"
            subject.onSecondWordChanged(validInput)
            val uiState = subject.formState.first()

            assertEquals(uiState.secondWordFieldState, TextFieldUIState(content = validInput, isValid = true))
        }

    @Test
    fun `Subject does not set sequence request if the fields are empty`() {
        subject.onSubmitRequest()

        verifyNoInteractions(requestStoreMock)
    }

    @Test
    fun `Subject sets expected sequence request on valid input`() {
        subject.onFirstIntegerChanged(TEST_REQUEST.firstInteger.toString())
        subject.onFirstWordChanged(TEST_REQUEST.firstWord)
        subject.onSecondIntegerChanged(TEST_REQUEST.secondInteger.toString())
        subject.onSecondWordChanged(TEST_REQUEST.secondWord)
        subject.onSequenceLimitChanged(TEST_REQUEST.sequenceLimit.toString())
        subject.onSubmitRequest()

        verify(requestStoreMock, times(1)).setSequenceRequest(TEST_REQUEST)
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
