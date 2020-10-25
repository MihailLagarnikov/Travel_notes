package com.twosmalpixels.travel_notes.core.offline_mode

import androidx.lifecycle.MutableLiveData

class OflineModeUseCase : IOflineModeUseCase {
    private val isOfline = MutableLiveData<Boolean>()

    override fun getModeLiveData(): MutableLiveData<Boolean> {
        return isOfline
    }

    override fun setOflineMode(isOfline: Boolean) {
        this.isOfline.value = isOfline
    }
}