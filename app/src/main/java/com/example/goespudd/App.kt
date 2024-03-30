package com.example.goespudd

import android.app.Application
import com.example.goespudd.data.source.local.database.AppDatabase

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}