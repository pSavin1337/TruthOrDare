package com.lospollos.truthordare.data

import java.io.File

class TaskLoader {

    fun loadTask(link: String?): TaskLoaderRequest {
        val taskFile: File
        if (link == null) {
            return TaskLoaderRequest.FileNotExist
        } else {
            taskFile = File(link)
        }
        return if(taskFile.exists()) {
            if (taskFile.isFile && taskFile.canRead()) {
                val tasks = ArrayList<String>()
                taskFile.forEachLine { task ->
                    tasks.add(task)
                }
                TaskLoaderRequest.Success(tasks)
            } else {
                TaskLoaderRequest.ErrorFileType
            }
        } else {
            TaskLoaderRequest.FileNotExist
        }
    }

}