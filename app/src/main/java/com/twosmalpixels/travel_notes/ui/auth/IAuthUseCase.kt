package com.twosmalpixels.travel_notes.ui.auth

import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth


interface IAuthUseCase {
    fun isNeedAuth(auth: FirebaseAuth): Boolean

    fun getUserId(): String?

    suspend fun isAuthSuccessful(
        auth: FirebaseAuth,
        authCredential: AuthCredential,
        fragmentActivity: FragmentActivity
    ): Boolean

    suspend fun createUserWithEmailAndPassword(
        auth: FirebaseAuth,
        email: String,
        password: String
    ): Boolean

    suspend fun signInWithEmailAndPassword(
        auth: FirebaseAuth,
        email: String,
        password: String
    ): Boolean

    suspend fun resetWithEmail(auth: FirebaseAuth, email: String): Boolean
}