package com.manishwarade.bookapp

import android.app.Application
import android.content.Context

class BookApp : Application() {


    override fun onCreate() {


        super.onCreate()


        appContext = this.applicationContext
    }

    companion object {

        lateinit var appContext: Context
    }
}