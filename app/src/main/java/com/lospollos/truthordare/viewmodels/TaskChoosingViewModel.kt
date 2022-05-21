package com.lospollos.truthordare.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun onChoosingActivityResult(context: Context, result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedFile = result.data?.data
            requestLiveData.value = tasksUseCase.getTasks(context, selectedFile)
        }
    }

}