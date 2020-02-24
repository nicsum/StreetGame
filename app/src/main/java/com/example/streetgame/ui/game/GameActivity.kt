package com.example.streetgame.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.streetgame.R
import com.example.streetgame.ui.records.RecordsActivity


class GameActivity : AppCompatActivity() {

    companion object{
        fun start(context: Context){
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_game)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, GameFragment())
            .commit()
    }


    fun showRecords(recordId: Int){
        RecordsActivity.start(this, recordId)
        finish()
    }

}