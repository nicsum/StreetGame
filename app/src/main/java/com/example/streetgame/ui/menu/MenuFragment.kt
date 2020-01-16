package com.example.streetgame.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streetgame.R
import kotlinx.android.synthetic.main.fr_menu.*

class MenuFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        startGameButton.setOnClickListener {
            (activity as MenuActivity).startGame()
        }
    }
}