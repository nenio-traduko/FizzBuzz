package com.dalvarado.fizzbuzz.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.math.BigInteger

class MultiplesSequenceItemUnitTest {
    private lateinit var subject: MultiplesSequenceItem

    @Before
    fun setUp() {
        subject =
            MultiplesSequenceItem(
                value = BigInteger.ZERO,
                sequenceRequest = TEST_REQUEST,
            )
    }

    @Test
    fun `Subject supports addition with BigInteger`() =
        assertEquals(
            subject + BigInteger.ONE,
            MultiplesSequenceItem(value = BigInteger.ONE, sequenceRequest = TEST_REQUEST),
        )

    @Test
    fun `Subject supports comparison with BigInteger`() {
        assertTrue(subject < BigInteger.ONE)
    }

    @Test
    fun `Subject is equal to objects of the same type with the same property values`() =
        assertEquals(
            subject,
            MultiplesSequenceItem(
                value = BigInteger.ZERO,
                sequenceRequest = TEST_REQUEST,
            ),
        )

    @Test
    fun `Subject is not equal to objects of a different type`() {
        assertNotEquals(subject, BigInteger.ZERO)
    }

    @Test
    fun `Subject returns first word when the value is a multiple of the first factor`() {
        assertEquals((subject + MULTIPLE_OF_FIRST_FACTOR).toString(), TEST_REQUEST.firstWord)
    }

    @Test
    fun `Subject returns second word when the value is a multiple of the second factor`() {
        assertEquals((subject + MULTIPLE_OF_SECOND_FACTOR).toString(), TEST_REQUEST.secondWord)
    }

    @Test
    fun `Subject returns both words when the value is a multiple of both factors`() =
        assertEquals(
            (subject + MULTIPLE_OF_BOTH_FACTORS).toString(),
            TEST_REQUEST.firstWord + TEST_REQUEST.secondWord,
        )

    @Test
    fun `Subject generates a hashCode`() {
        assertNotNull(subject.hashCode())
    }

    companion object {
        val TEST_REQUEST =
            SequenceRequest(
                firstInteger = BigInteger("12"),
                firstWord = "baba",
                secondInteger = BigInteger("5"),
                secondWord = "booey",
                sequenceLimit = BigInteger("9999999"),
            )
        val MULTIPLE_OF_FIRST_FACTOR = BigInteger("144")
        val MULTIPLE_OF_SECOND_FACTOR = BigInteger("10")
        val MULTIPLE_OF_BOTH_FACTORS = BigInteger("60")
    }
}
