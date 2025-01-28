package com.example.finalpam

import android.app.Application
import com.example.finalpam.repository.AppContainer
import com.example.finalpam.repository.PropertiContainer

class PropertiApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container=PropertiContainer()
    }
}
