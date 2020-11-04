package com.twosmalpixels.travel_notes

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module


class TravelNoteApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf<Module>(appModule))
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()
    }
}