package com.dalvarado.fizzbuzz.model.repository

import com.dalvarado.fizzbuzz.model.SequenceRequest
import com.dalvarado.fizzbuzz.model.repository.api.SequenceRequestStore

class SequenceRequestRepository private constructor(
    private var sequenceRequest: SequenceRequest = SequenceRequest.EMPTY,
) : SequenceRequestStore {
    override fun setSequenceRequest(request: SequenceRequest) {
        sequenceRequest = request
    }

    override fun getSequenceRequest(): SequenceRequest = sequenceRequest

    companion object {
        val INSTANCE = SequenceRequestRepository()
    }
}
