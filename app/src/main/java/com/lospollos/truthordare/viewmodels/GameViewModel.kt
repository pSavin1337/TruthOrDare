package com.lospollos.truthordare.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lospollos.truthordare.domain.entities.Players

class GameViewModel: ViewModel() {

    val playerName = MutableLiveData<String>()

    fun onNextTask() {
        playerName.value = Players.chooseRandomPlayer()
    }

}