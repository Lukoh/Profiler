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

package com.goforer.profiler.presentation.stateholder.business

import androidx.lifecycle.ViewModel
import com.goforer.profiler.data.source.network.api.Params
import timber.log.Timber
import javax.inject.Singleton

/*
 * Just use the below code if you take data from the Backend server.
 */
@Singleton
abstract class BaseViewModel : ViewModel() {
    open fun trigger(replyCount: Int, params: Params) {
        Timber.d("Triggered Params")
    }
}