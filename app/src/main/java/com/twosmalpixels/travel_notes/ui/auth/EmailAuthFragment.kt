package com.twosmalpixels.travel_notes.ui.auth

import BaseFragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.setVisibility
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.MainActivity
import kotlinx.android.synthetic.main.email_auth_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EmailAuthFragment : BaseFragment(), TextWatcher, OnTouchListener {

    val MIN_LENGHT_EMAIL = 6
    val MIN_LENGHT_PASSWORD = 6

    private lateinit var authViewModel: AuthViewModel
    private var isRegistration = true

    override fun getToolbarParam() = ToolbarParam(getString(R.string.registration))

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
                if (isRegistration) {
                    createUserWithEmailAndPassword(
                        enter_email_edit_text.text.toString().trim(),
                        double_password_edit_text.text.toString().trim()
                    )
                } else {
                    signInWithEmailAndPassword(
                        enter_email_edit_text.text.toString().trim(),
                        enter_password_edit_text.text.toString().trim()
                    )
                }
            }
        }
        show_top_password.setOnTouchListener(this)
        show_bottom_password.setOnTouchListener(this)

        enter_bottom.setOnClickListener {
            isRegistration = !isRegistration
            setMode(isRegistration)
        }
        enter_top.setOnClickListener {
            isRegistration = !isRegistration
            setMode(isRegistration)
        }
        setMode(isRegistration)
    }

    private fun setMode(isRegistration: Boolean) {
        double_password_card.setVisibility(isRegistration)
        enter_top.setText(if (isRegistration) R.string.has_account else R.string.need_registration)
        enter_bottom.setText(if (isRegistration) R.string.has_account_entry else R.string.registration_now)
        text_fogot_password.setVisibility(!isRegistration)
        button_email_auth_save.setText(if (isRegistration) R.string.registration_now else R.string.has_account_entry)
    }

    override fun afterTextChanged(p0: Editable?) {
        val emailLenght = enter_email_edit_text.text?.length ?: 0
        val passwordLenght = enter_password_edit_text?.text?.length ?: 0
        val validBtn = (emailLenght > MIN_LENGHT_EMAIL)
                && (enter_password_edit_text.text != null
                && passwordLenght > MIN_LENGHT_PASSWORD
                && (!isRegistration || double_password_edit_text.text != null)
                && (!isRegistration || enter_password_edit_text.text.toString().equals(
            double_password_edit_text.text.toString()
        )))
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

    private suspend fun signInWithEmailAndPassword(email: String, password: String) {
        val job = GlobalScope.async {
            authViewModel.signInWithEmailAndPassword(
                (activity!! as MainActivity).auth,
                email,
                password
            )
        }
        if (job.await()) {
            findNavController().navigate(R.id.action_emailAuthFragment_to_youTravelsFragment)
        } else {
            Snackbar.make(requireView(), "Authentication Failed.", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (view) {
            show_top_password -> {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        enter_password_edit_text.setTransformationMethod(
                            HideReturnsTransformationMethod()
                        )
                        show_top_password.setImageResource(R.drawable.ic_eye_open)
                    }
                    MotionEvent.ACTION_UP -> {
                        enter_password_edit_text.setTransformationMethod(
                            PasswordTransformationMethod()
                        )
                        show_top_password.setImageResource(R.drawable.ic_eye_close)
                    }
                }
            }
            show_bottom_password -> {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        double_password_edit_text.setTransformationMethod(
                            HideReturnsTransformationMethod()
                        )
                        show_bottom_password.setImageResource(R.drawable.ic_eye_open)
                    }
                    MotionEvent.ACTION_UP -> {
                        double_password_edit_text.setTransformationMethod(
                            PasswordTransformationMethod()
                        )
                        show_bottom_password.setImageResource(R.drawable.ic_eye_close)
                    }
                }
            }

        }
        return true
    }
}