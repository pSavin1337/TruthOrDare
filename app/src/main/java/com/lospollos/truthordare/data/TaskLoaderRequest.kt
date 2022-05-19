package com.lospollos.truthordare.data

sealed class TaskLoaderRequest {
    class Success(val tasks: List<String>) : TaskLoaderRequest()
    object FileNotExist : TaskLoaderRequest()
    object ErrorFileType : TaskLoaderRequest()
}
