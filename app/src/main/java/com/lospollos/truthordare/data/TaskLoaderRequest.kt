package com.lospollos.truthordare.data

sealed class TaskLoaderRequest {
    class Success(val tasks: List<String>, val questions: List<String>) : TaskLoaderRequest()
    object FileNotExist : TaskLoaderRequest()
    object ErrorFileType : TaskLoaderRequest()
}
