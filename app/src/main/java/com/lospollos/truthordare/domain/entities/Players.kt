package com.lospollos.truthordare.domain.entities

import kotlin.random.Random

object Players {

    var players = ArrayList<String>()

    private const val startIndex = 0
    private var lastPlayer: String? = null

    fun chooseRandomPlayer(): String {
        var currentPlayer = players[Random.nextInt(startIndex, players.size - 1)]
        while(currentPlayer == lastPlayer) {
            currentPlayer = players[Random.nextInt(startIndex, players.size - 1)]
        }
        lastPlayer = currentPlayer
        return currentPlayer
    }

    fun forEachPlayers(onIteration: () -> Unit) {
        players.forEach { _ ->
            onIteration()
        }
    }

}