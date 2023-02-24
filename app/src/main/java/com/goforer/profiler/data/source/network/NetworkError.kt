package com.goforer.profiler.data.source.network

data class NetworkError(val detail: List<ErrorBody>) {
    data class ErrorBody(
        val loc: List<String>,
        var msg: String = ERROR_DEFAULT,
        val type: String
    )

    companion object {
        const val ERROR_DEFAULT = "An unexpected error has occurred"

        const val ERROR_SERVICE_UNAVAILABLE = 503
        const val ERROR_SERVICE_BAD_GATEWAY = 502

        const val ERROR_SERVICE_UNPROCESSABLE_ENTITY = 422
    }
}