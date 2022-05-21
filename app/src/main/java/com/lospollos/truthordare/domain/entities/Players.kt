package com.lospollos.truthordare.domain.entities

import kotlin.random.Random

class Players {

    var players = ArrayList<String>()

    private val startIndex = 0
    private var lastPlayer: String? = null

    fun getRandomPlayer(): String {
        var currentPlayer = players[Random.nextInt(startIndex, players.size)]
        while(currentPlayer == lastPlayer) {
            currentPlayer = players[Random.nextInt(startIndex, players.size)]
        }
        lastPlayer = currentPlayer
        return currentPlayer
    }

}