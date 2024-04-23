package com.example.goespudd.presentation.splashscreen

import androidx.lifecycle.ViewModel
import com.example.goespudd.data.repository.UserRepository

class SplashViewModel(private val repository: UserRepository) : ViewModel() {
    fun isUserLoggedIn() = repository.isLoggedIn()



}