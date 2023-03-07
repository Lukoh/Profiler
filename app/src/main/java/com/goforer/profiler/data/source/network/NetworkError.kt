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