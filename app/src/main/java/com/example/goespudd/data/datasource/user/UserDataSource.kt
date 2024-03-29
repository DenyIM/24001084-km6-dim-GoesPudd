package com.example.goespudd.data.datasource.user

interface UserDataSource {
    fun isUsingDarkMode(): Boolean
    fun setUsingDarkMode(isUsingDarkMode: Boolean)
}