package com.twosmalpixels.travel_notes.views.person

import com.twosmalpixels.travel_notes.R

enum class PersonType(val title: Int, val subTitle: Int, val textIsOne: Int, val textIsMore: Int) {
    ADULTS(R.string.adults, R.string.adults_sub, R.string.adults_one, R.string.adults_many),
    CHILDREN(R.string.children, R.string.children_sub, R.string.children_one, R.string.children_many),
    BABIES(R.string.babies, R.string.babies_sub, R.string.babies_one, R.string.babies_many)


}