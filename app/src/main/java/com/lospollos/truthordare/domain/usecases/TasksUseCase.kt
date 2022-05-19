package com.lospollos.truthordare.domain.usecases

import android.net.Uri
import com.lospollos.truthordare.data.TaskLoader
import com.lospollos.truthordare.data.TaskLoaderRequest
import com.lospollos.truthordare.data.TasksStorage

class TasksUseCase {

    private val taskLoader = TaskLoader()

    fun getTasks(fileUri: Uri): TaskLoaderRequest = taskLoader.loadTask(fileUri.toString())
    fun getRandomTask(): String = TasksStorage.tasks.chooseRandomTask()
    fun setTasksList(tasksList: List<String>) {
        TasksStorage.tasks.tasks = tasksList as ArrayList<String>
    }

}