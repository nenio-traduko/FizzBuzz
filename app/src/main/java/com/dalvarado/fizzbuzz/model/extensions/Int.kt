package com.dalvarado.fizzbuzz.model.extensions

fun Int.isDivisible(by: Int): Boolean {
    return this % by == 0
}
