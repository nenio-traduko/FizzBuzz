package com.dalvarado.fizzbuzz.model

import android.util.Log

class SequenceRequestRepository(
  private val sequenceRequestList: MutableList<SequenceRequest> = mutableListOf()
) {

  fun addSequenceRequest(request: SequenceRequest) {
    Log.d(null, "Adding sequenceRequest:[$request]")
    sequenceRequestList.add(request)
  }
}