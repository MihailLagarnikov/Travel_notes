package com.twosmalpixels.travel_notes.ui.expense_all

import com.twosmalpixels.travel_notes.views.shedule.SheduleData
import com.twosmalpixels.travel_notes.views.shedule.SheduleSpinerData

interface IExpenceAllUseCase {
    fun getDefaultShdulelist(): ArrayList<SheduleData>
    fun getDefaultShdulelistSpiner(): ArrayList<SheduleSpinerData>
    fun getDefaultExpenseList(): ArrayList<ExpenceData>
}