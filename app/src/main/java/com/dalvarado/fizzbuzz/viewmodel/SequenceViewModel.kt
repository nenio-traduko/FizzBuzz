package com.dalvarado.fizzbuzz.viewmodel

import androidx.lifecycle.ViewModel
import com.dalvarado.fizzbuzz.model.repository.SequenceRepository
import com.dalvarado.fizzbuzz.model.repository.SequenceRequestRepository
import com.dalvarado.fizzbuzz.model.repository.api.SequenceRequestStore

class SequenceViewModel(
    sequenceRequestRepository: SequenceRequestStore = SequenceRequestRepository.INSTANCE,
    sequenceRepository: SequenceRepository = SequenceRepository(),
) : ViewModel() {
    val sequence: Sequence<String> =
        sequenceRepository.getSequence(
            sequenceRequestRepository.getSequenceRequest(),
        )
    val sequenceSize: Int = sequenceRequestRepository.getSequenceRequest().sequenceLimit
}
