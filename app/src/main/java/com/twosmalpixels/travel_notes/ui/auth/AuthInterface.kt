package com.twosmalpixels.travel_notes.ui.auth

import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope

interface AuthInterface {
    fun isNeedAuth(auth: FirebaseAuth): Boolean
    fun getUserId(): String?
    suspend fun getAuthRezult(
        auth: FirebaseAuth,
        authCredential: AuthCredential,
        fragmentActivity: FragmentActivity
    ): () -> Boolean

    suspend fun createUserWithEmailAndPassword(
        auth: FirebaseAuth,
        email: String,
        password: String
    ): () -> Boolean

    suspend fun signInWithEmailAndPassword(
        auth: FirebaseAuth,
        email: String,
        password: String
    ): () -> Boolean

}