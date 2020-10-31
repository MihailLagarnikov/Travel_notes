package com.twosmalpixels.travel_notes.ui.review_expence

import BaseFragment
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.createStringFromLocation
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import com.twosmalpixels.travel_notes.core.repositoriy.IFairbaseStorageBase
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.MainActivity
import com.twosmalpixels.travel_notes.ui.expense_all.getDrawableForNumber
import com.twosmalpixels.travel_notes.ui.expense_all.getTextForNumber
import kotlinx.android.synthetic.main.review_expence_fragment.*
import org.koin.java.standalone.KoinJavaComponent

class ReviewExpenceFragment : BaseFragment() {

    private val iFairbaseStorageBase by KoinJavaComponent.inject(
        IFairbaseStorageBase::class.java
    )
    private lateinit var reviewViewModel: ReviewExpenceViewModel

    override fun getToolbarParam() = ToolbarParam(reviewViewModel.expenceData!!.data)

    override fun getLayout() = R.layout.review_expence_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reviewViewModel =
            ViewModelProviders.of(requireActivity()).get(ReviewExpenceViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
        val expenceData = reviewViewModel.expenceData
        expenceData?.run {
            image_main_reviw_expence.setImageResource(getDrawableForNumber(expenceData.category.toInt()))
            text_main_reviw_expence.setText(getTextForNumber(expenceData.category.toInt()))
            amount_review_expence.text = expenceData.amount.toString()
            text_currency_review_expence.text = expenceData.currencyIso

            card_comment_reviw_expence.setVisibility(expenceData.comment.isNotEmpty())
            text_comment_reviw_expence.text = expenceData.comment

            card_foto_reviw_expence.setVisibility(expenceData.imageUrl.isNotEmpty())
            if (expenceData.imageUrl.isNotEmpty()) {
                iFairbaseStorageBase.loadBitMap(
                    expenceData.imageUrl,
                    (requireActivity() as MainActivity).storage,
                    image_reviw_expence
                )
            }

            card_location_reviw_expence.setVisibility(!expenceData.lat.isNaN())
            text_location_reviw_expence.text =
                createStringFromLocation(expenceData.lat, expenceData.lon)
        }
    }
}