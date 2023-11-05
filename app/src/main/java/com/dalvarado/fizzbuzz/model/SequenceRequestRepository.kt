package com.dalvarado.fizzbuzz.model

class SequenceRequestRepository private constructor(
  private var sequenceRequest: SequenceRequest = SequenceRequest.EMPTY
) {

  fun setSequenceRequest(request: SequenceRequest) {
    sequenceRequest = request
  }

  fun getSequenceRequest(): SequenceRequest = sequenceRequest

  companion object {
    val INSTANCE = SequenceRequestRepository()
  }
}