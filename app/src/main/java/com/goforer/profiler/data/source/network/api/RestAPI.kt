package com.goforer.profiler.data.source.network.api

import com.goforer.profiler.data.source.model.entity.source.response.ProfileResponse
import com.goforer.profiler.data.source.network.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface RestAPI {
    @GET("/profile/profiles")
    fun getProfiles(): Flow<ApiResponse<ProfileResponse>>
}