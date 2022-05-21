package com.lospollos.truthordare.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import com.lospollos.truthordare.Constants.DOWNLOAD_AUTHORITY
import com.lospollos.truthordare.Constants.DOWNLOAD_URI
import com.lospollos.truthordare.Constants.EXTERNAL_STORAGE_AUTHORITY

class UriHelper {

    fun uriToPath(context: Context, uri: Uri): String? {
        return when (uri.authority) {
            EXTERNAL_STORAGE_AUTHORITY -> {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    val androidDir = "Android"
                    return context
                        .getExternalFilesDir(null)
                        ?.absolutePath
                        ?.split(androidDir)?.get(0) + "/" + split[1]
                }
                null
                // TODO handle non-primary volumes
            }
            DOWNLOAD_AUTHORITY -> {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse(DOWNLOAD_URI),
                    java.lang.Long.valueOf(id)
                )
                getDataColumn(context, contentUri)
            }
            else -> {
                null
            }
        }
    }

    private fun getDataColumn(context: Context, uri: Uri): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(
                uri,
                projection,
                null,
                null,
                null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(columnIndex)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

}