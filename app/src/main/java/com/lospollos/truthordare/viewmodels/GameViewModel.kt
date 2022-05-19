package com.lospollos.truthordare.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lospollos.truthordare.domain.entities.Players
import com.lospollos.truthordare.domain.usecases.PlayersUseCase
import com.lospollos.truthordare.domain.usecases.TasksUseCase

class GameViewModel: ViewModel() {

    val playerNameLiveData = MutableLiveData<String>()
    val taskLiveData = MutableLiveData<String>()

    private val playersUseCase = PlayersUseCase()
    private val tasksUseCase = TasksUseCase()

    fun onNextPlayer() {
        playerNameLiveData.value = playersUseCase.getRandomPlayer()
        taskLiveData.value = tasksUseCase.getRandomTask()
    }

    fun onReroll() {
        taskLiveData.value = tasksUseCase.getRandomTask()
    }

}