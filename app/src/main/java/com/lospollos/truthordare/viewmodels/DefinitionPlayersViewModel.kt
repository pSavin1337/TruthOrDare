package com.lospollos.truthordare.viewmodels

import androidx.lifecycle.ViewModel
import com.lospollos.truthordare.domain.usecases.PlayersUseCase

class DefinitionPlayersViewModel: ViewModel() {

    private val playersUseCase = PlayersUseCase()

    fun onPlayersListReady(playersList: List<String>) {
        playersUseCase.setPlayersList(playersList)
    }

}