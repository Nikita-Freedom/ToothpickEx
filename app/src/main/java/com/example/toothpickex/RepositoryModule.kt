package com.example.toothpickex

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import toothpick.config.Module


class RepositoryModule(context: Context) : Module() {
    init {
        bind(UserRepository::class.java).to(PrefUserRepository::class.java)
            .singleton()

        val sharedPreferences: SharedPreferences = context.getSharedPreferences("app.prefs", MODE_PRIVATE)
        bind(SharedPreferences::class.java).toInstance(sharedPreferences)
    }
}