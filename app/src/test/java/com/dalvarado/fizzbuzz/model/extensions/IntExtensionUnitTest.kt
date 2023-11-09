package com.dalvarado.fizzbuzz.model.extensions

import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class IntExtensionUnitTest {
    @Test
    fun `Is divisible returns true for a divisible pair`() {
        val numerator = 16
        val denominator = 4
        assertTrue(numerator.isDivisible(by = denominator))
    }

    @Test
    fun `Is divisible returns false for a non divisible pair`() {
        val numerator = 4
        val denominator = 16
        assertFalse(numerator.isDivisible(by = denominator))
    }

    @Test
    fun `Is divisible throws an exception when the denominator is zero`() {
        val numerator = 12
        val denominator = 0
        assertThrows(ArithmeticException::class.java) { numerator.isDivisible(denominator) }
    }

    @Test
    fun `Is divisible always returns true when the numerator is zero`() {
        val numerator = 0
        val denominator = -1
        assertTrue(numerator.isDivisible(by = denominator))
    }
}
