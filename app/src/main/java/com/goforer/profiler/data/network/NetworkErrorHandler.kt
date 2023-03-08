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

package com.goforer.profiler.data.network

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