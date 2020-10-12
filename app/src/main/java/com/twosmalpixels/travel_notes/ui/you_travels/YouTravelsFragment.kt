package com.twosmalpixels.travel_notes.ui.you_travels

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.MainActivity
import kotlinx.android.synthetic.main.you_travels_fragment.*

class YouTravelsFragment: BaseFragment() {
    private lateinit var viewModel: YouTravelsViewModel
    override fun getToolbarParam() = ToolbarParam(getString(R.string.you_travels),false)

    override fun getLayout() = R.layout.you_travels_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(YouTravelsViewModel::class.java)
        content.layoutManager = LinearLayoutManager(context)
        val defTravelItem =  TravelsItem.createDefaultItem(resources)
        val mainActivity = (requireActivity() as MainActivity)
        val yuTravelsAdapter = YouTravelsAdapter(mainActivity.storage, {clickItem(it)});
        content.adapter = yuTravelsAdapter
        progressViewModel.showProgress.value = true
        viewModel.getYouTravelsList(mainActivity.db).observe(this, Observer {
            if (!it.contains(defTravelItem)) {
                it.add(defTravelItem)
            }
            yuTravelsAdapter.setNewList(it)
            progressViewModel.showProgress.value = false
        })
    }

    private fun clickItem(travelsItem: TravelsItem){
        if (travelsItem.equals(TravelsItem.createDefaultItem(resources))){
            findNavController().navigate(R.id.action_youTravelsFragment_to_newTravelFragment)
        }else{
            expenseAllViewModel.toolbareName = travelsItem.title
            expenseAllViewModel.travelsItem = travelsItem
            findNavController().navigate(R.id.action_youTravelsFragment_to_expenceAllFragment)
        }
    }
}