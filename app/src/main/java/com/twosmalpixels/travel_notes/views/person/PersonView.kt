package com.twosmalpixels.travel_notes.views.person

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.views.person.PersonType
import com.twosmalpixels.travel_notes.views.person.PersonViewListener
import kotlinx.android.synthetic.main.person_view.view.*


class PersonView: ConstraintLayout {
    private var personType: Int? = null
    set(value) {
        field = value
        setText()
    }
    private val ONE = 1
    private val TWO = 2
    private val THRE = 3
    private val PROBEL = " "
    private var viewListener: PersonViewListener? = null

    var countPerson = 0
    set(value) {
        val isZerro = value <= 0
        if (isZerro) {
            field = 0
        }else{
            field = value
        }
        button_minus_person.isEnabled = !isZerro
        person_count.text = field.toString()
        if (viewListener != null){
            viewListener!!.changePersonCount(field)
        }
    }


    constructor(ctx: Context) : super(ctx) {
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        val a =
            context.obtainStyledAttributes(attrs, R.styleable.PersonView)

        personType = a.getInt(R.styleable.PersonView_personType, 1)
        a.recycle()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.person_view, this)
        button_minus_person.isEnabled = false
        button_minus_person.setOnClickListener { countPerson-- }
        button_plus_person.setOnClickListener { countPerson++ }
    }

    private fun setText(){
        val personType = getPersonType(personType)
        title_person_view.setText(personType.title)
        subtitle_person_view.setText(personType.subTitle)
    }

    private fun getPersonType(type: Int?): PersonType{
       return when(type){
            ONE -> PersonType.ADULTS
           TWO -> PersonType.CHILDREN
           THRE -> PersonType.BABIES
           else -> PersonType.ADULTS
        }
    }

    private fun getOtputWord(count: Int, personType: PersonType): String{

        return if (count == 1){
            context.getString(personType.textIsOne)
        }else{
            context.getString(personType.textIsMore)
        }
    }

    fun getOutputText(): String{
        return if (countPerson == 0){
            ""
        }else{
            return countPerson.toString() + PROBEL + getOtputWord(countPerson, getPersonType(personType))
        }

    }

    fun setViewListener(viewListener: PersonViewListener){
        this.viewListener = viewListener
    }
}