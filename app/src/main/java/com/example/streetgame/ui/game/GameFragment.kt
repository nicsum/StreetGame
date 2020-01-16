package com.example.streetgame.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streetgame.AppDelegate
import com.example.streetgame.R
import com.example.streetgame.network.createStreetGameService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fr_game.*
import kotlinx.coroutines.*

class GameFragment : Fragment(){


    var job : Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postRecordButton.setOnClickListener {

            if(!recordIsCorrect()){
                showMessage("Некорректный ввод")
                return@setOnClickListener
            }

            val record = getScore().toInt()
            postRecord(record)
        }

    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    private fun postRecord(score: Int){
        val service = createStreetGameService()
        val username = getUsername() ?: return

        job =  CoroutineScope(Dispatchers.IO).launch{
            val res = service.postResults("street", username, 1, score, 0)
            if(res.isSuccessful){
                withContext(Dispatchers.Main){
                    val id = res.body()?.id ?: return@withContext
                    showRecords(id)
                }
            }else{
                showMessage("Не удалось запостить рекорд")
            }
        }
    }

    private fun recordIsCorrect(): Boolean{
        return getScore().isNotEmpty()
    }

    private fun showMessage(message: String){
        Snackbar.make(view!!, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showRecords(recordsId: Int){
        (activity as GameActivity).showRecords(recordsId)
    }

    private fun getScore() = enterScoreEditText.text.toString()

    private fun getUsername() = (context?.applicationContext as AppDelegate)
        .userDataRepository
        ?.username

}