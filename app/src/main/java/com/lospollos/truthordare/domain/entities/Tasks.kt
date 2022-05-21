package com.lospollos.truthordare.domain.entities

import kotlin.random.Random

class Tasks {

    var tasks = ArrayList<String>()
    var questions = ArrayList<String>()

    private val startIndex = 0
    private var lastTask: String? = null
    private var lastQuestion: String? = null

    fun getRandomTask(): String {
        var currentTask = tasks[Random.nextInt(startIndex, tasks.size)]
        while(currentTask == lastTask) {
            currentTask = tasks[Random.nextInt(startIndex, tasks.size)]
        }
        lastTask = currentTask
        return currentTask
    }

    fun getRandomQuestion(): String {
        var currentQuestion = questions[Random.nextInt(startIndex, questions.size)]
        while(currentQuestion == lastQuestion) {
            currentQuestion = questions[Random.nextInt(startIndex, questions.size)]
        }
        lastQuestion = currentQuestion
        return currentQuestion
    }

}