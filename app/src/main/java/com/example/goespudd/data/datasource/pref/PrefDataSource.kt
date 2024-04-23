package com.example.goespudd.data.datasource.pref

import com.example.goespudd.data.source.local.pref.UserPreference

interface PrefDataSource {
    fun isUsingGridMode(): Boolean
    fun setUsingGridMode(isUsingDarkMode: Boolean)
}

class PrefDataSourceImpl(private val userPreference: UserPreference): PrefDataSource{
    override fun isUsingGridMode(): Boolean {
        return userPreference.isUsingGridMode()
    }

    override fun setUsingGridMode(isUsingDarkMode: Boolean) {
        return userPreference.setUsingGridMode(isUsingDarkMode)
    }

}