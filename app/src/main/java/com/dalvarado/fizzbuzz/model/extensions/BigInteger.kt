package com.dalvarado.fizzbuzz.model.extensions

import java.math.BigInteger

fun BigInteger.isDivisible(by: BigInteger): Boolean {
    return this.divideAndRemainder(by).last() == BigInteger.ZERO
}
