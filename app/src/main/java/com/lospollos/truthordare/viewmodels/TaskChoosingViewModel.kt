package com.lospollos.truthordare.viewmodels

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lospollos.truthordare.Constants.NOT_FOUND_TASK_REQUEST_CODE
import com.lospollos.truthordare.Constants.SUCCESS_TASK_REQUEST_CODE
import com.lospollos.truthordare.Constants.TYPE_ERROR_TASK_REQUEST_CODE
import com.lospollos.truthordare.data.TaskLoaderRequest
import com.lospollos.truthordare.domain.usecases.TasksUseCase

class TaskChoosingViewModel : ViewModel() {

    val requestLiveData = MutableLiveData<Int>()

    private val tasksUseCase = TasksUseCase()

    fun onChoosingFileButtonClick(resultLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent()
            .setType("text/plain")
            .setAction(Intent.ACTION_GET_CONTENT)

        val intentTitle = "Choose File"
        resultLauncher.launch(Intent.createChooser(intent, intentTitle))
    }

    fun onChoosingActivityResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedFile = result.data?.data
            val request = if (selectedFile == null) {
                TaskLoaderRequest.FileNotExist
            } else {
                tasksUseCase.getTasks(selectedFile)
            }
            when (request) {
                is TaskLoaderRequest.Success -> {
                    tasksUseCase.setTasksList(request.tasks)
                    requestLiveData.value = SUCCESS_TASK_REQUEST_CODE
                }
                is TaskLoaderRequest.FileNotExist -> requestLiveData.value =
                    NOT_FOUND_TASK_REQUEST_CODE
                is TaskLoaderRequest.ErrorFileType -> requestLiveData.value =
                    TYPE_ERROR_TASK_REQUEST_CODE
            }
        }
    }

}