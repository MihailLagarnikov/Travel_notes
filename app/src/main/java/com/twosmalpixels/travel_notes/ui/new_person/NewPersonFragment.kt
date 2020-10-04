package com.twosmalpixels.travel_notes.ui.new_person

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.DEF_EMPTY_STRING
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.views.person.PersonViewListener
import kotlinx.android.synthetic.main.new_person_fragment.*

class NewPersonFragment: BaseFragment(), PersonViewListener {
    private val ZERO = 0
    private val DEFIS = " • "


    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam(getString(R.string.who_travel))
    }

    override fun getLayout(): Int {
        return R.layout.new_person_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newPersonViewModel =ViewModelProviders.of(requireActivity()).get(NewPersonViewModel::class.java)
        button_new_person_save.isEnabled = false
        button_new_person_save.setOnClickListener {
            newPersonViewModel.setPerson(createPersonText())
            activity?.onBackPressed()
        }
        adult.setViewListener(this)
        children.setViewListener(this)
        baby.setViewListener(this)
    }

    private fun createPersonText(): String{
        val adultText = adult.getOutputText()
        val childrenText = children.getOutputText()
        val babyText = baby.getOutputText()
        val oneDefis = !adultText.isEmpty() && (!childrenText.isEmpty() || !babyText.isEmpty())
        val twoDefis = !childrenText.isEmpty() && !babyText.isEmpty()
        return (adultText + getDefis(oneDefis) + childrenText + getDefis(twoDefis) + babyText)
    }

    private fun getDefis(isVisibility: Boolean): String{
        return if (isVisibility){
            DEFIS
        }else{
            DEF_EMPTY_STRING
        }
    }

    override fun changePersonCount(count: Int) {
        button_new_person_save.isEnabled = adult.countPerson != ZERO || children.countPerson != ZERO || baby.countPerson != ZERO
    }
}