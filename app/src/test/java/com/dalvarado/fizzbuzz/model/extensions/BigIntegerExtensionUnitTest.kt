package com.dalvarado.fizzbuzz.model.extensions

import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import java.math.BigInteger

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BigIntegerExtensionUnitTest {
    @Test
    fun `Is divisible returns true for a divisible pair`() {
        val numerator = BigInteger("16")
        val denominator = BigInteger("4")
        assertTrue(numerator.isDivisible(by = denominator))
    }

    @Test
    fun `Is divisible returns false for a non divisible pair`() {
        val numerator = BigInteger("4")
        val denominator = BigInteger("16")
        assertFalse(numerator.isDivisible(by = denominator))
    }

    @Test
    fun `Is divisible throws an exception when the denominator is zero`() {
        val numerator = BigInteger("12")
        val denominator = BigInteger("0")
        assertThrows(ArithmeticException::class.java) { numerator.isDivisible(denominator) }
    }
}
