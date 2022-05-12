package com.lospollos.truthordare.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.lospollos.truthordare.R
import com.lospollos.truthordare.viewmodels.GameViewModel

class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var playerNameTextView: TextView
    private lateinit var nextTaskButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerNameTextView = view.findViewById(R.id.game_name_text_view)
        nextTaskButton = view.findViewById(R.id.game_next_task_button)
        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        gameViewModel.onNextTask()
        gameViewModel.playerName.observe(viewLifecycleOwner) { playerName ->
            playerNameTextView.text = playerName
        }
        nextTaskButton.setOnClickListener {
            gameViewModel.onNextTask()
        }
    }

}