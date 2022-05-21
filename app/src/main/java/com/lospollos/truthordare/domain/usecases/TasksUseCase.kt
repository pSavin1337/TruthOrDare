package com.lospollos.truthordare.domain.usecases

import android.net.Uri
import androidx.core.net.toFile
import com.lospollos.truthordare.data.TaskLoader
import com.lospollos.truthordare.data.TaskLoaderRequest
import com.lospollos.truthordare.data.TasksStorage
import android.content.ContentUris
import android.content.Context
import android.database.Cursor

import android.provider.DocumentsContract

import android.os.Environment
import com.lospollos.truthordare.App.Companion.context
import com.lospollos.truthordare.Constants
import com.lospollos.truthordare.data.UriHelper


class TasksUseCase {

    private val taskLoader = TaskLoader()
    private val uriHelper = UriHelper()

    fun getTasks(selectedFile: Uri?): Int {
        val request = if (selectedFile == null) {
            TaskLoaderRequest.FileNotExist
        } else {
            taskLoader.loadTask(uriHelper.uriToPath(selectedFile))
        }
        return when (request) {
            is TaskLoaderRequest.Success -> {
                setTasksList(request.tasks)
                Constants.SUCCESS_TASK_REQUEST_CODE
            }
            is TaskLoaderRequest.FileNotExist -> Constants.NOT_FOUND_TASK_REQUEST_CODE
            is TaskLoaderRequest.ErrorFileType -> Constants.TYPE_ERROR_TASK_REQUEST_CODE
        }
    }
    fun getRandomTask(): String = TasksStorage.tasks.chooseRandomTask()
    private fun setTasksList(tasksList: List<String>) {
        TasksStorage.tasks.tasks = tasksList as ArrayList<String>
    }

}