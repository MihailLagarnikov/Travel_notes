package com.twosmalpixels.travel_notes.ui.auth

import BaseFragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.MainActivity
import kotlinx.android.synthetic.main.email_auth_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EmailAuthFragment : BaseFragment(), TextWatcher {

    val MIN_LENGHT_EMAIL = 6
    val MIN_LENGHT_PASSWORD = 6

    private lateinit var authViewModel: AuthViewModel

    override fun getToolbarParam() = ToolbarParam()

    override fun getLayout() = R.layout.email_auth_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        enter_email_edit_text.addTextChangedListener(this)
        enter_password_edit_text.addTextChangedListener(this)
        double_password_edit_text.addTextChangedListener(this)
        double_password_edit_text.setOnFocusChangeListener { view, b ->
            {
                val passwordLenght = enter_password_edit_text?.text?.length ?: 0
                if (passwordLenght < MIN_LENGHT_PASSWORD && b) {
                    Snackbar.make(
                        requireView(),
                        R.string.auth_email_password_lenght,
                        Snackbar.LENGTH_LONG
                    )
                }
            }
        }
        button_email_auth_save.setOnClickListener {
            GlobalScope.launch {
                createUserWithEmailAndPassword(
                    enter_email_edit_text.text.toString(),
                    double_password_edit_text.text.toString()
                )
            }
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        val emailLenght = enter_email_edit_text.text?.length ?: 0
        val passwordLenght = enter_password_edit_text?.text?.length ?: 0
        val validBtn = (emailLenght > MIN_LENGHT_EMAIL)
                && (enter_password_edit_text.text != null
                && passwordLenght > MIN_LENGHT_PASSWORD
                && double_password_edit_text.text != null
                && enter_password_edit_text.text.toString().equals(double_password_edit_text.text.toString()))
        button_email_auth_save.isEnabled = validBtn
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    private suspend fun createUserWithEmailAndPassword(email: String, password: String) {
        val job = GlobalScope.async {
            authViewModel.createUserWithEmailAndPassword(
                (activity!! as MainActivity).auth,
                email,
                password
            )
        }
        if (job.await()) {
            Snackbar.make(requireView(), R.string.auth_success, Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_emailAuthFragment_to_youTravelsFragment)
        } else {
            Snackbar.make(requireView(), "Authentication Failed.", Snackbar.LENGTH_LONG).show()
        }
    }
}