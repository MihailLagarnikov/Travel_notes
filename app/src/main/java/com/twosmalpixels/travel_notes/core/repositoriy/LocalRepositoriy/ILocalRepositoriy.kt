package com.twosmalpixels.travel_notes.core.repositoriy.LocalRepositoriy

import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData

interface ILocalRepositoriy {

    fun saveNewTravel(travelsItem: TravelsItem)
    fun getAllTravels(): ArrayList<TravelsItem>
    fun saveExpenceData(expenceData: ExpenceData, travelsName: String)
    fun loadExpenceDataList(travelName: String): ArrayList<ExpenceData>
}