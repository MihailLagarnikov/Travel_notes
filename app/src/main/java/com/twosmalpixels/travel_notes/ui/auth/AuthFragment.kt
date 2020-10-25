package com.twosmalpixels.travel_notes.ui.auth

import BaseFragment
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.GoogleAuthProvider
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import com.twosmalpixels.travel_notes.ui.MainActivity
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AuthFragment : BaseFragment() {
    private val RC_SIGN_IN = 6969

    private lateinit var authViewModel: AuthViewModel
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun getLayout() = R.layout.auth_fragment

    override fun getToolbarParam() = ToolbarParam(visibl = View.GONE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        auth_google.setOnClickListener {
            startActivityForResult(getGoogleSiginIntent(), RC_SIGN_IN)
        }
        auth_email.setOnClickListener { findNavController().navigate(R.id.action_authFragment_to_emailAuthFragment) }
    }

    private fun getGoogleSiginIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        return googleSignInClient.signInIntent;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                GlobalScope.launch { firebaseAuthWithGoogle(account.idToken!!) }
            } catch (e: ApiException) {
                Snackbar.make(requireView(), "Authentication Failed.", Snackbar.LENGTH_LONG).show()

            }
        }
    }

    private suspend fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val job = GlobalScope.async {
            authViewModel.isAuthSuccessful(
                (activity!! as MainActivity).auth,
                credential,
                requireActivity()
            )
        }
        if (job.await()) {
            // Sign in success, update UI with the signed-in user's information
            findNavController().navigate(R.id.action_authFragment_to_youTravelsFragment)
        } else {
            Snackbar.make(requireView(), "Authentication Failed.", Snackbar.LENGTH_LONG).show()
        }
    }

}