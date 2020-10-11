package com.twosmalpixels.travel_notes.ui.calendar_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.savvi.rangedatepicker.CalendarPickerView
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.ui.new_travel.NewTravelsViewModel
import kotlinx.android.synthetic.main.calendarik_dialog.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalenarikDialog: DialogFragment() {

    private val diferentDate: Long = 1000L * 60 * 60 * 24 * 2000
    private lateinit var newTravelsViewModel: NewTravelsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calendarik_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newTravelsViewModel =
            ViewModelProviders.of(requireActivity()).get(NewTravelsViewModel::class.java)
        val calendar = Calendar.getInstance()
        val startDate = Date(calendar.time.time)
        val endDate = Date(calendar.time.time + diferentDate)
        calendar_view.init(startDate, endDate, SimpleDateFormat("MMMM, YYYY", Locale.getDefault()))
            .inMode(getRangeMode())

        button_calendar_save.setOnClickListener {
            if (getRangeMode().equals(CalendarPickerView.SelectionMode.RANGE)) {
                newTravelsViewModel.chooseDates.value = calendar_view.selectedDates as ArrayList<Date>?
            } else {
                newTravelsViewModel.chooseDate.value = calendar_view.selectedDate
            }
            dismiss()
        }
    }

    private fun getRangeMode(): CalendarPickerView.SelectionMode{
        return if (newTravelsViewModel.isRangeMode){
            CalendarPickerView.SelectionMode.RANGE
        }else{
            CalendarPickerView.SelectionMode.SINGLE
        }
    }


}