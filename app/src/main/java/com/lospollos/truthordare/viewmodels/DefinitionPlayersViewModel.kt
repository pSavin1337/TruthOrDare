package com.lospollos.truthordare.viewmodels

import androidx.lifecycle.ViewModel
import com.lospollos.truthordare.domain.entities.Players

class DefinitionPlayersViewModel: ViewModel() {

    fun onPlayersListReady(playersList: List<String>) {
        Players.players = playersList as ArrayList<String>
    }

}