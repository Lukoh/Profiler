package com.goforer.profiler.data.source.mediator

import androidx.annotation.MainThread
import com.goforer.profiler.data.source.model.entity.source.response.ProfileResponse
import com.goforer.profiler.data.source.network.response.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber

abstract class DataMediator<T> constructor(viewModelScope: CoroutineScope, replyCount: Int = 0) {
    private val resource by lazy {
        Resource()
    }

    internal val asSharedFlow = flow {
        emit(resource.loading(Status.LOADING))
        load().collect { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    emit(resource.success(apiResponse.body))
                }

                is ApiEmptyResponse -> {
                    emit(resource.success(""))
                }

                is ApiErrorResponse -> {
                    Timber.e("Network-Error: ${apiResponse.errorMessage}")
                    emit(resource.error(apiResponse.errorMessage, apiResponse.statusCode))
                    onNetworkError(apiResponse.errorMessage, apiResponse.statusCode)
                }
            }
        }
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        replay = replyCount
    )

    protected open suspend fun onNetworkError(errorMessage: String, errorCode: Int) {
    }

    @MainThread
    protected abstract fun load(): Flow<ApiResponse<T>>
}