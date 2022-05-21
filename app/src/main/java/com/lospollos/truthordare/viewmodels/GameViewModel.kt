package com.lospollos.truthordare.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lospollos.truthordare.Constants.NOT_CHOSEN
import com.lospollos.truthordare.Constants.QUESTION
import com.lospollos.truthordare.Constants.TASK
import com.lospollos.truthordare.domain.usecases.PlayersUseCase
import com.lospollos.truthordare.domain.usecases.TasksUseCase

class GameViewModel: ViewModel() {

    val playerNameLiveData = MutableLiveData<String>()
    val taskLiveData = MutableLiveData<String>()

    private var taskOrQuestion = NOT_CHOSEN

    private val playersUseCase = PlayersUseCase()
    private val tasksUseCase = TasksUseCase()

    fun onNextPlayer() {
        playerNameLiveData.value = playersUseCase.getRandomPlayer()
    }

    fun onTaskPicked() {
        taskOrQuestion = TASK
        taskLiveData.value = tasksUseCase.getRandomTask()
    }

    fun onQuestionPicked() {
        taskOrQuestion = QUESTION
        taskLiveData.value = tasksUseCase.getRandomQuestion()
    }

    fun onReroll() {
        taskLiveData.value = when (taskOrQuestion) {
            TASK -> tasksUseCase.getRandomTask()
            QUESTION -> tasksUseCase.getRandomQuestion()
            else -> ""
        }
    }

}