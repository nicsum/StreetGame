package com.example.streetgame.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streetgame.AppDelegate
import com.example.streetgame.R
import com.example.streetgame.user.UserDataRepository
import kotlinx.android.synthetic.main.fr_auth.*

class AuthFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_auth, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterButton.setOnClickListener {
            if(!usernameIsCorrect()){
                usernameInput.error = "Некорректный никнейм"
                return@setOnClickListener
            }

            val username = usernameInputEditText.text.toString()
            startMenu(username)
        }
    }


    private fun startMenu(username: String){
        val udr = UserDataRepository(username)
        (context?.applicationContext as AppDelegate).userDataRepository = udr
        (activity as AuthActivity).startMenu()
    }
    private fun usernameIsCorrect(): Boolean{
        return usernameInputEditText.text.toString().isNotEmpty()
    }




}