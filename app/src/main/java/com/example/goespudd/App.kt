package com.example.goespudd

import android.app.Application
import com.example.goespudd.data.source.local.database.AppDatabase

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}