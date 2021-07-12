package com.example.toothpickex

import android.content.SharedPreferences

import javax.inject.Inject

class PrefUserRepository @Inject constructor(private val sharedPreferences: SharedPreferences) :
    UserRepository {
    override val user: User
        get() = User(
            sharedPreferences.getString(KEY_NAME, "")!!,
            sharedPreferences.getString(KEY_EMAIL, "")!!
        )

    override fun saveUser(user: User?) {
        sharedPreferences.edit()
            .putString(KEY_NAME, user!!.name)
            .putString(KEY_EMAIL, user.email)
            .apply()
    }

    companion object {
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_EMAIL = "KEY_EMAIL"
    }
}