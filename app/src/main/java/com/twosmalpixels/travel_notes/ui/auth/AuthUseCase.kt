package com.twosmalpixels.travel_notes.ui.auth

import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.twosmalpixels.travel_notes.core.repositoriy.SharedPref.ISharedPrefHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AuthUseCase(val authInterface: AuthInterface, val sharedPrefHelper: ISharedPrefHelper) : IAuthUseCase {

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
        val job = GlobalScope.async(Dispatchers.IO) {
            run(
                authInterface.getAuthRezult(
                    auth,
                    authCredential,
                    fragmentActivity
                )
            )
        }
        val rez = job.await()
        return rez
    }

    override suspend fun createUserWithEmailAndPassword(
        auth: FirebaseAuth,
        email: String,
        password: String
    ): Boolean {
        val job = GlobalScope.async(Dispatchers.IO) {
            run(
                authInterface.createUserWithEmailAndPassword(
                    auth,
                    email,
                    password
                )
            )
        }
        val rez = job.await()
        return rez
    }

    override suspend fun signInWithEmailAndPassword(
        auth: FirebaseAuth,
        email: String,
        password: String
    ): Boolean {
        val job = GlobalScope.async(Dispatchers.IO) {
            run(
                authInterface.signInWithEmailAndPassword(
                    auth,
                    email,
                    password
                )
            )
        }
        val rez = job.await()
        return rez
    }

    override suspend fun resetWithEmail(auth: FirebaseAuth, email: String): Boolean {
        val job = GlobalScope.async(Dispatchers.IO) {
            run(
                authInterface.resetWithEmail(
                    auth,
                    email
                )
            )
        }
        val rez = job.await()
        return rez
    }
}


