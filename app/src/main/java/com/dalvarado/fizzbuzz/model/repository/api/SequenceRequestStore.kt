package com.dalvarado.fizzbuzz.model.repository.api

import com.dalvarado.fizzbuzz.model.SequenceRequest

interface SequenceRequestStore {
    fun setSequenceRequest(request: SequenceRequest)

    fun getSequenceRequest(): SequenceRequest
}
