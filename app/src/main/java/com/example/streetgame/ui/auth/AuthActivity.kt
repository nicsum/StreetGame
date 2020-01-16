package com.example.streetgame.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.streetgame.R
import com.example.streetgame.ui.menu.MenuActivity

class AuthActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.ac_auth)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, AuthFragment())
            .commit()
    }


    fun startMenu(){
        MenuActivity.start(this)
        finish()
    }

}