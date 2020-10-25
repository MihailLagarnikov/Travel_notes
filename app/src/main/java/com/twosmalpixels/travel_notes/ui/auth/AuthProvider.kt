package com.twosmalpixels.travel_notes.ui.auth

import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.twosmalpixels.travel_notes.ui.auth.AuthInterface
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class AuthProvider : AuthInterface {
    private var auth: FirebaseAuth? = null

    override fun isNeedAuth(auth: FirebaseAuth): Boolean {
        this.auth = auth
        return auth.currentUser == null
    }

    override fun getUserId(): String? {
        return if (auth != null) {
            auth?.currentUser?.uid
        } else {
            null
        }
    }

    override suspend fun getAuthRezult(
        auth: FirebaseAuth,
        authCredential: AuthCredential,
        fragmentActivity: FragmentActivity
    ): () -> Boolean {
        val job = auth.signInWithCredential(authCredential)
        val rez = job.await()
        return { rez.user != null }
    }

    override suspend fun createUserWithEmailAndPassword(
        auth: FirebaseAuth,
        email: String,
        password: String
    ): () -> Boolean {
        val job = auth.createUserWithEmailAndPassword(email, password)
        val rez = job.await()
        return { rez.user != null }
    }

    override suspend fun signInWithEmailAndPassword(
        auth: FirebaseAuth,
        email: String,
        password: String
    ): () -> Boolean {
        val job = auth.signInWithEmailAndPassword(email, password)
        val rez = job.await()
        return { rez.user != null }
    }
}