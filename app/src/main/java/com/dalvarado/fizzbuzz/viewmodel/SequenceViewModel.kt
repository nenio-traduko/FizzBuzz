package com.dalvarado.fizzbuzz.viewmodel

import androidx.lifecycle.ViewModel
import com.dalvarado.fizzbuzz.model.SequenceRepository
import com.dalvarado.fizzbuzz.model.SequenceRequestRepository

class SequenceViewModel(
    sequenceRequestRepository: SequenceRequestRepository = SequenceRequestRepository.INSTANCE,
    sequenceRepository: SequenceRepository = SequenceRepository(),
) : ViewModel() {
    val sequenceList: List<String> =
        sequenceRepository.getSequence(
            sequenceRequestRepository.getSequenceRequest(),
        ).map { sequenceItem ->
            sequenceItem.toString()
        }.toList()
}
