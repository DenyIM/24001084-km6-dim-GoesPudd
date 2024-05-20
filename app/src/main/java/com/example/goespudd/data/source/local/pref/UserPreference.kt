package com.example.goespudd.data.source.local.pref

import android.content.Context
import android.util.Log
import com.example.goespudd.utils.SharedPreferenceUtils
import com.example.goespudd.utils.SharedPreferenceUtils.set

interface UserPreference {
    fun isUsingGridMode(): Boolean

    fun setUsingGridMode(isUsingGridMode: Boolean)
}

class UserPreferenceImpl(private val context: Context) : UserPreference {
    private val preference = SharedPreferenceUtils.createPreference(context, PREF_NAME)

    override fun isUsingGridMode(): Boolean {
        return preference.getBoolean(KEY_IS_USING_GRID_MODE, false)
    }

    override fun setUsingGridMode(isUsingGridMode: Boolean) {
        preference[KEY_IS_USING_GRID_MODE] = isUsingGridMode
        Log.e(TAG, "Menyimpan nilai $isUsingGridMode ke dalam User Pref")
    }

    companion object {
        const val TAG = "User Preferences"
        const val PREF_NAME = "goespudd-pref"
        const val KEY_IS_USING_GRID_MODE = "KEY_IS_USING_GRID_MODE"
    }
}
