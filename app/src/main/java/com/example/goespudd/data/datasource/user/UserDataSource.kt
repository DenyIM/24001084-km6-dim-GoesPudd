package com.example.goespudd.data.datasource.user

import com.example.goespudd.data.source.local.pref.UserPreference

interface UserDataSource {
    fun isUsingGridMode(): Boolean

    fun setUsingGridMode(isUsingGrid: Boolean)
}

class UserDataSourceImpl(private val userPreference: UserPreference) : UserDataSource {
    override fun isUsingGridMode(): Boolean {
        return userPreference.isUsingGridMode()
    }

    override fun setUsingGridMode(isUsingGrid: Boolean) {
        userPreference.setUsingGridMode(isUsingGrid)
    }
}
