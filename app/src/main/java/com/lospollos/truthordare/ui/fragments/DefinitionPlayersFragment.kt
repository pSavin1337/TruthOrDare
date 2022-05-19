package com.lospollos.truthordare.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lospollos.truthordare.Constants.INITIAL_PLAYERS_COUNT
import com.lospollos.truthordare.R
import com.lospollos.truthordare.ui.activities.MainActivity
import com.lospollos.truthordare.ui.adapters.PlayersRecyclerViewAdapter
import com.lospollos.truthordare.viewmodels.DefinitionPlayersViewModel

class DefinitionPlayersFragment : Fragment() {

    private lateinit var playersRecyclerView: RecyclerView
    private lateinit var addPlayerButton: Button
    private lateinit var startGameButton: Button
    private lateinit var definitionPlayersViewModel: DefinitionPlayersViewModel

    private val playersList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_definition_players, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        definitionPlayersViewModel =
            ViewModelProvider(this)[DefinitionPlayersViewModel::class.java]

        addPlayerButton = view.findViewById(R.id.players_add_player_button)
        startGameButton = view.findViewById(R.id.players_start_button)
        playersRecyclerView = view.findViewById(R.id.players_recycler_view)
        playersRecyclerView.layoutManager = LinearLayoutManager(context)
        playersRecyclerView.adapter = PlayersRecyclerViewAdapter(INITIAL_PLAYERS_COUNT)
        //TODO: При добавлении большого количества игроков кнопка "+" вылезает за экран
        addPlayerButton.setOnClickListener {
            val newPlayersCount =
                ++(playersRecyclerView.adapter as PlayersRecyclerViewAdapter).playersCount
            (playersRecyclerView.adapter as PlayersRecyclerViewAdapter).notifyItemInserted(
                newPlayersCount - 1
            )
        }

        startGameButton.setOnClickListener {
            playersRecyclerView.children.iterator().forEachRemaining { item ->
                (item as ViewGroup).children.iterator().forEachRemaining { child ->
                    if (child is TextView) {
                        playersList.add(child.text.toString())
                    }
                }
            }
            definitionPlayersViewModel.onPlayersListReady(playersList)
            (activity as MainActivity).openGameFragment()
        }

    }

}