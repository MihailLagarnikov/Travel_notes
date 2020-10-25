package com.twosmalpixels.travel_notes.core.offline_mode

import androidx.lifecycle.MutableLiveData

interface IOflineModeUseCase {
    fun getModeLiveData(): MutableLiveData<Boolean>
    fun setOflineMode(isOfline: Boolean)
}