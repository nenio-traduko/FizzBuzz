package com.dalvarado.fizzbuzz.model.utils

import android.util.Log

class Logger {
    fun error(
        tag: String,
        message: String,
    ) {
        Log.e(tag, message)
    }

    fun debug(
        tag: String,
        message: String,
    ) {
        Log.d(tag, message)
    }

    fun info(
        tag: String,
        message: String,
    ) {
        Log.i(tag, message)
    }
}
