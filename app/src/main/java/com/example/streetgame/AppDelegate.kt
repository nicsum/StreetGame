package com.example.streetgame

import android.app.Application
import com.example.streetgame.user.UserDataRepository

class AppDelegate : Application() {


    var userDataRepository: UserDataRepository? = null
    override fun onCreate() {
        super.onCreate()
    }
}