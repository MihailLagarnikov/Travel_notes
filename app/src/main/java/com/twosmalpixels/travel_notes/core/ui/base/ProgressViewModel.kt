package com.twosmalpixels.travel_notes.core.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProgressViewModel: ViewModel() {
    val showProgress = MutableLiveData<Boolean>()
    init {
        showProgress.value = false
    }
}