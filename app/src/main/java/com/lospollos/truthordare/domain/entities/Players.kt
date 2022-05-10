package com.lospollos.truthordare.domain.entities

import kotlin.random.Random

object Players {

    var players = ArrayList<String>()

    fun chooseRandomPlayer(): String = players[Random(players.size).nextInt()]

    fun deletePlayer(playerId: Int) = players.removeAt(playerId)

    fun addPlayer(playerName: String) = players.add(playerName)

    fun forEachPlayers(onIteration: () -> Unit) {
        players.forEach { _ ->
            onIteration()
        }
    }

}