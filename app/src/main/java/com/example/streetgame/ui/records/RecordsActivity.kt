package com.example.streetgame.ui.records

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.streetgame.R

class RecordsActivity : AppCompatActivity(){

    companion object{

        private const val RECORD_ID_KEY = "RECORD_ID_KEY"

        fun getRecordId(bundle: Bundle): Int{
            return bundle.getInt(RECORD_ID_KEY, -1)
        }

        fun start(context: Context, recordId: Int){
            val intent = Intent(context, RecordsActivity::class.java)
            intent.putExtra(RECORD_ID_KEY, recordId)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, RecordsFragment()  )
            .commit()
        setContentView(R.layout.ac_records)
    }

}