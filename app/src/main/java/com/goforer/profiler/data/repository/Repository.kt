/*
 * Copyright (C) 2021 The Android Open Source Project by Lukoh Nam, goForer
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

package com.goforer.profiler.data.repository

import com.goforer.profiler.data.source.network.NetworkErrorHandler
import com.goforer.profiler.data.source.network.api.RestAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract class Repository<Resource> {
    companion object {
        internal var replyCount = 0
    }

    @Inject
    lateinit var restAPI: RestAPI

    @Inject
    lateinit var networkErrorHandler: NetworkErrorHandler

    open fun invalidatePagingSource() {}

    protected fun handleNetworkError(errorMessage: String) {
        networkErrorHandler.handleError(errorMessage)
    }
}