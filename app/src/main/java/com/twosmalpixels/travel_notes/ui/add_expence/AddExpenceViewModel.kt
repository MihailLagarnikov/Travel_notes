package com.twosmalpixels.travel_notes.ui.add_expence

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddExpenceViewModel: ViewModel() {
    val isHaveLocationPermision = MutableLiveData<Boolean>()

    init {
        isHaveLocationPermision.value = false
    }
}