package com.twosmalpixels.travel_notes.ui.expence_all_offline

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.MainActivity
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import com.twosmalpixels.travel_notes.ui.expense_all.ExpenseAdapter
import kotlinx.android.synthetic.main.expence_all_offline_fragment.*
import kotlinx.android.synthetic.main.expence_all_offline_fragment.card_image_add_new_expence
import kotlinx.android.synthetic.main.expence_all_offline_fragment.image_add_new_expence


class ExpenceAllOfflineFragment : BaseFragment() {

    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam(expenseAllViewModel.toolbareName, backButtonVisiblity = false)
    }

    override fun getLayout(): Int {
        return R.layout.expence_all_offline_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expense_recycler_all_offline_fragment.layoutManager = LinearLayoutManager(context)
        val expAdapter = ExpenseAdapter { clickExpenceItem(it) }
        expense_recycler_all_offline_fragment.adapter = expAdapter


        expenseAllViewModel.expenceDataList.observe(this, Observer {
            expAdapter.setNewList(it)
        })

        progressViewModel.showProgress.value = true
        expenseAllViewModel.getExpenceList((requireActivity() as MainActivity).db)
            .observe(this, Observer {
                expenseAllViewModel.setTotalExpenceWithCurrency(it)
                expenseAllViewModel.setCategoryExpenceList(it)
                if (it != null && !it.isEmpty()) {
                    expAdapter.setNewList(it)
                }
                progressViewModel.showProgress.value = false
                card_image_add_new_expence.setVisibility(it.size > 0)
            })

        image_add_new_expence.setOnClickListener { findNavController().navigate(R.id.action_expenceAllOfflineFragment_to_addExpenceFragment) }
    }

    private fun clickExpenceItem(expenceData: ExpenceData): Boolean {
        if (expenceData.equals(ExpenceData.getEmptyData())) {
            findNavController().navigate(R.id.action_expenceAllOfflineFragment_to_addExpenceFragment)
        }
        return true
    }
}