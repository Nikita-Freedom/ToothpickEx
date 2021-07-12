package com.example.toothpickex

import android.app.Application
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class App: Application() {
    override fun onCreate() {
        super.onCreate()
         val appScope: Scope = Toothpick.openScope("APP")
        appScope.installModules(RepositoryModule(applicationContext))

    }
}