package com.example.gemini.task5

import android.app.Application

/**
 * Created by Gemini on 12.03.2018.
 */
class App :Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance:App
            private set
    }
}