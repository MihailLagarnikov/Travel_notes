package com.twosmalpixels.travel_notes.core.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twosmalpixels.travel_notes.pojo.ToolbarParam

class ToolbarViewModel: ViewModel() {
    val toolbarParam = MutableLiveData<ToolbarParam>()
    init {
        toolbarParam.value = ToolbarParam()
    }
}