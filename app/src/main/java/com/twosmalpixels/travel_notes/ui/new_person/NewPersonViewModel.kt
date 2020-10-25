package com.twosmalpixels.travel_notes.ui.new_person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.DEF_EMPTY_STRING
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.ISharedPrefHelper
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.LAST_PERSON
import org.koin.java.standalone.KoinJavaComponent

class NewPersonViewModel : ViewModel() {
    private val sharedPref: ISharedPrefHelper by KoinJavaComponent.inject(
        ISharedPrefHelper::class.java
    )

    private val person = MutableLiveData<String>()

    init {
        person.value = sharedPref.loadText(LAST_PERSON, DEF_EMPTY_STRING)
    }

    fun getPerson(): LiveData<String> {
        return person
    }

    fun setPerson(newPerson: String) {
        if (!newPerson.equals(DEF_EMPTY_STRING)) {
            sharedPref.saveText(LAST_PERSON, newPerson)
        }
        person.value = newPerson
    }
}