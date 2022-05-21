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
        val fileType = "text/plain"
        val intent = Intent()
            .setType(fileType)
            .setAction(Intent.ACTION_GET_CONTENT)

        val intentTitle = "Choose File"
        resultLauncher.launch(Intent.createChooser(intent, intentTitle))
    }

    fun onChoosingActivityResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedFile = result.data?.data
            requestLiveData.value = tasksUseCase.getTasks(selectedFile)
        }
    }

}