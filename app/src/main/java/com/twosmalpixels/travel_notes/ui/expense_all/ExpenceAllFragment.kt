package com.twosmalpixels.travel_notes.ui.expense_all

import BaseFragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.MainActivity
import com.twosmalpixels.travel_notes.ui.add_expence.ExpenceData
import com.twosmalpixels.travel_notes.ui.review_expence.ReviewExpenceViewModel
import kotlinx.android.synthetic.main.expence_all_fragment.*

class ExpenceAllFragment : BaseFragment() {

    private lateinit var reviewViewModel: ReviewExpenceViewModel

    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam(expenseAllViewModel.toolbareName)
    }

    override fun getLayout(): Int {
        return R.layout.expence_all_fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reviewViewModel = ViewModelProviders.of(requireActivity()).get(ReviewExpenceViewModel::class.java)
        expense_recycler_all_fragment.layoutManager = LinearLayoutManager(context)
        val expAdapter = ExpenseAdapter { clickExpenceItem(it) }
        expense_recycler_all_fragment.adapter = expAdapter

        expenseAllViewModel.categoryExpenceList.observe(this, Observer {
            shedule_view.setListSheduleData(it, expAdapter.itemCount == 0)
        })

        expenseAllViewModel.curencyDataList.observe(this, Observer {
            shedule_view.setListSpinerData(it)
        })

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
                } else {
                    expAdapter.setNewList(expenseAllViewModel.getDefaultExpenseList())
                }
                progressViewModel.showProgress.value = false
                card_image_add_new_expence.setVisibility(it.size > 0)
                shedule_view.isStartState(it.size == 0)
            })

        image_add_new_expence.setOnClickListener { findNavController().navigate(R.id.action_expenceAllFragment_to_addExpenceFragment) }

        oflineModeUseCase.getModeLiveData().observe(this, Observer {
            if (it) {
                    findNavController().navigate(R.id.action_expenceAllFragment_to_expenceAllOfflineFragment)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shara_expence){
            expenseAllViewModel.currencyToShare = shedule_view.getChoosenCurrency()
            findNavController().navigate(R.id.action_expenceAllFragment_to_shareExpenceFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun clickExpenceItem(expenceData: ExpenceData): Boolean {
        if (expenceData.equals(ExpenceData.getEmptyData())) {
            findNavController().navigate(R.id.action_expenceAllFragment_to_addExpenceFragment)
        }else{
            reviewViewModel.expenceData = expenceData
            findNavController().navigate(R.id.action_expenceAllFragment_to_reviewExpenceFragment)
        }
        return true
    }
}