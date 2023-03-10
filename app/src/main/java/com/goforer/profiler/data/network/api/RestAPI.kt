/*
 * Copyright (C) 2023 The Android Open Source Project by Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.profiler.data.network.api

import com.goforer.profiler.data.model.datum.response.mynetwork.PersonResponse
import com.goforer.profiler.data.model.datum.response.notification.NotificationResponse
import com.goforer.profiler.data.network.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface RestAPI {
    @GET("/profile/{user_id}/profiles")
    fun getMyNetworks(
        @Path("user_id") userId: String
    ): Flow<ApiResponse<PersonResponse>>

    @GET("")
    fun getNotifications(
        @Path("user_id") userId: String
    ): Flow<ApiResponse<NotificationResponse>>

    @GET("/profile/members")
    fun getMembers(): Flow<ApiResponse<PersonResponse>>
}