package com.twosmalpixels.travel_notes.ui.expense_all

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.MainActivity
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import kotlinx.android.synthetic.main.expence_all_fragment.*

class ExpenceAllFragment: BaseFragment() {

    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam(expenseAllViewModel.toolbareName)
    }

    override fun getLayout(): Int {
        return R.layout.expence_all_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expense_recycler_all_fragment.layoutManager = LinearLayoutManager(context)
        val expAdapter = ExpenseAdapter{clickExpenceItem(it)}
        expense_recycler_all_fragment.adapter = expAdapter

        expenseAllViewModel.categoryExpenceList.observe(this, Observer {
            shedule_view.setListSheduleData(it)
        })

        expenseAllViewModel.curencyDataList.observe(this, Observer {
            shedule_view.setListSpinerData(it)
        })

        expenseAllViewModel.expenceDataList.observe(this, Observer {
            expAdapter.setNewList(it)
        })

        progressViewModel.showProgress.value = true
        expenseAllViewModel.getExpenceList((requireActivity() as MainActivity).db).observe(this, Observer {
            if (!it.contains(ExpenceData.getEmptyData())) {
                it.add(ExpenceData.getEmptyData())
            }
            expenseAllViewModel.setTotalExpenceWithCurrency(it)
            expenseAllViewModel.setCategoryExpenceList(it)
            expAdapter.setNewList(it)
            progressViewModel.showProgress.value = false
        })
    }

    private fun clickExpenceItem(expenceData: ExpenceData){
        if (expenceData.equals(ExpenceData.getEmptyData())){
            findNavController().navigate(R.id.action_expenceAllFragment_to_addExpenceFragment)
        }
    }
}