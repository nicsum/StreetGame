package com.example.streetgame.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.streetgame.R
import com.example.streetgame.ui.game.GameActivity

class MenuActivity : AppCompatActivity() {

    companion object{

        fun start(context: Context){
            val intent = Intent(context, MenuActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.ac_menu)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MenuFragment())
            .commit()
    }

    fun startGame(){
        GameActivity.start(this)
    }


}