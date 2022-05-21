package com.lospollos.truthordare.domain.usecases

import android.content.Context
import android.net.Uri
import com.lospollos.truthordare.Constants
import com.lospollos.truthordare.data.TaskLoader
import com.lospollos.truthordare.data.TaskLoaderRequest
import com.lospollos.truthordare.data.TasksStorage
import com.lospollos.truthordare.data.UriHelper

class TasksUseCase {

    private val taskLoader = TaskLoader()
    private val uriHelper = UriHelper()

    fun getTasks(context: Context, selectedFile: Uri?): Int {
        val request = if (selectedFile == null) {
            TaskLoaderRequest.FileNotExist
        } else {
            taskLoader.loadTask(uriHelper.uriToPath(context, selectedFile))
        }
        return when (request) {
            is TaskLoaderRequest.Success -> {
                setTasksAndQuestionsList(request.tasks, request.questions)
                Constants.SUCCESS_TASK_REQUEST_CODE
            }
            is TaskLoaderRequest.FileNotExist -> Constants.NOT_FOUND_TASK_REQUEST_CODE
            is TaskLoaderRequest.ErrorFileType -> Constants.TYPE_ERROR_TASK_REQUEST_CODE
        }
    }
    fun getRandomTask(): String = TasksStorage.tasks.getRandomTask()
    fun getRandomQuestion(): String = TasksStorage.tasks.getRandomQuestion()
    private fun setTasksAndQuestionsList(tasksList: List<String>, questionsList: List<String>) {
        TasksStorage.tasks.tasks = tasksList as ArrayList<String>
        TasksStorage.tasks.questions = questionsList as ArrayList<String>
    }

}