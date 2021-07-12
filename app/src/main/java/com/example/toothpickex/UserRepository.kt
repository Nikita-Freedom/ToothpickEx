package com.example.toothpickex

interface UserRepository {
    val user: User?

    fun saveUser(user: User?)
}