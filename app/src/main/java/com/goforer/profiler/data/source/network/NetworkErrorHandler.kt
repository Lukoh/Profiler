package com.goforer.profiler.data.source.network

import android.content.Context
import com.google.gson.Gson
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkErrorHandler
@Inject
constructor(val context: Context) {
    internal fun handleError(errorMessage: String) {
        runCatching {
            val networkError = Gson().fromJson(errorMessage, NetworkError::class.java)
            networkError.detail[0].type.let {
                when (it) {
                    "INVALID_SESSION" -> {
                    }
                    "OBSOLETE_VERSION" -> {
                    }
                }
            }
        }.onFailure { e ->
            Timber.d("Exception $e")
        }
    }
}