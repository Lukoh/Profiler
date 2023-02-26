package com.goforer.profiler.data.source.network.api

import com.goforer.profiler.data.source.model.entity.source.response.ProfileResponse
import com.goforer.profiler.data.source.network.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface RestAPI {
    @GET("/profile/{user_id}/profiles")
    fun getProfiles(
        @Path("news_id") newsId: String
    ): Flow<ApiResponse<ProfileResponse>>
}