package com.lospollos.truthordare.data

import com.lospollos.truthordare.Constants.TASKS_QUESTIONS_DELIMITER
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
                try {
                    val tasks = ArrayList<String>()
                    val questions = ArrayList<String>()
                    var isTasks = true
                    taskFile.forEachLine { line ->
                        when {
                            line.trim('\n', ' ', '\t') == TASKS_QUESTIONS_DELIMITER -> {
                                isTasks = false
                            }
                            isTasks -> {
                                tasks.add(line)
                            }
                            else -> {
                                questions.add(line)
                            }
                        }
                    }
                    TaskLoaderRequest.Success(tasks, questions)
                } catch (e: Exception) {
                    TaskLoaderRequest.ErrorFileType
                }
            } else {
                TaskLoaderRequest.ErrorFileType
            }
        } else {
            TaskLoaderRequest.FileNotExist
        }
    }

}