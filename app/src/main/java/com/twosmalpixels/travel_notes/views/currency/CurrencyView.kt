package com.twosmalpixels.travel_notes.views.currency

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.twosmalpixels.travel_notes.R

class CurrencyView: ConstraintLayout {

    constructor(ctx: Context) : super(ctx) {
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.shedule_view, this)
    }
}