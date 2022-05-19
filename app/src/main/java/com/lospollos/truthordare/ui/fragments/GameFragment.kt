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
    private lateinit var taskTextView: TextView
    private lateinit var nextTaskButton: Button
    private lateinit var rerollButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerNameTextView = view.findViewById(R.id.game_name_text_view)
        taskTextView = view.findViewById(R.id.game_task_text_view)
        nextTaskButton = view.findViewById(R.id.game_next_task_button)
        rerollButton = view.findViewById(R.id.game_reroll_task_button)
        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        gameViewModel.onNextPlayer()
        gameViewModel.playerNameLiveData.observe(viewLifecycleOwner) { playerName ->
            playerNameTextView.text = playerName
        }
        gameViewModel.taskLiveData.observe(viewLifecycleOwner) { task ->
            taskTextView.text = task
        }
        nextTaskButton.setOnClickListener {
            gameViewModel.onNextPlayer()
        }
        rerollButton.setOnClickListener {
            gameViewModel.onReroll()
        }
    }

}