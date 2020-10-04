package com.twosmalpixels.travel_notes.ui.auth

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import org.koin.java.standalone.KoinJavaComponent.inject

class AuthViewModel(): ViewModel() {
private val authUseCase: IAuthUseCase by inject(IAuthUseCase::class.java)
    fun isNeedAuth(auth: FirebaseAuth)= authUseCase.isNeedAuth(auth)

    suspend fun isAuthSuccessful(
        auth: FirebaseAuth,
        authCredential: AuthCredential,
        fragmentActivity: FragmentActivity
    ): Boolean{
        return authUseCase.isAuthSuccessful(auth, authCredential, fragmentActivity)
    }
}