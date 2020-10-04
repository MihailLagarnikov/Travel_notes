package com.twosmalpixels.travel_notes.ui.choose_travel_skin

import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.repositoriy.IFairbaseStorageBase
import org.koin.java.standalone.KoinJavaComponent

class ChooseSkinUseCase : IChooseSkinUseCase {
    private val iFairbaseStorageBase by KoinJavaComponent.inject(
        IFairbaseStorageBase::class.java
    )

    override fun getSkinList(): ArrayList<Int> {
        return arrayListOf(
            R.drawable.ic_img_new_travel_default,
            R.drawable.ic_img_new_travel_default,
            R.drawable.ic_img_new_travel_default,
            R.drawable.ic_img_new_travel_default,
            R.drawable.ic_img_new_travel_default
        )
    }
}