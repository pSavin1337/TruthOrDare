package com.lospollos.truthordare.domain.entities

import kotlin.random.Random

class Tasks {

    var tasks = ArrayList<String>()

    private val startIndex = 0
    private var lastTask: String? = null

    fun chooseRandomTask(): String {
        var currentTask = tasks[Random.nextInt(startIndex, tasks.size - 1)]
        while(currentTask == lastTask) {
            currentTask = tasks[Random.nextInt(startIndex, tasks.size - 1)]
        }
        lastTask = currentTask
        return currentTask
    }

}