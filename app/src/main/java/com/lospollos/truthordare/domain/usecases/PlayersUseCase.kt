package com.lospollos.truthordare.domain.usecases

import com.lospollos.truthordare.data.PlayersStorage

class PlayersUseCase {

    fun getRandomPlayer(): String = PlayersStorage.players.chooseRandomPlayer()
    fun setPlayersList(playersList: List<String>) {
        PlayersStorage.players.players = playersList as ArrayList<String>
    }

}