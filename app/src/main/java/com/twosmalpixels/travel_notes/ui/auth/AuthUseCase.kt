package com.twosmalpixels.travel_notes.ui.auth

import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AuthUseCase(val authInterface: AuthInterface): IAuthUseCase {

    override fun isNeedAuth(auth: FirebaseAuth): Boolean {
        return authInterface.isNeedAuth(auth)
    }

    override fun getUserId(): String? {
        return authInterface.getUserId()
    }

    override suspend fun isAuthSuccessful(
        auth: FirebaseAuth,
        authCredential: AuthCredential,
        fragmentActivity: FragmentActivity
    ): Boolean {
        val job = GlobalScope.async(Dispatchers.IO) { run(authInterface.getAuthRezult(auth, authCredential, fragmentActivity)) }
        val rez = job.await()
        return rez
    }
}


