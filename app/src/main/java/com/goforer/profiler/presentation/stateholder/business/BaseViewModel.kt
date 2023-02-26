package com.goforer.profiler.presentation.stateholder.business

import androidx.lifecycle.ViewModel
import com.goforer.profiler.data.source.network.api.Params

/*
 * Just use the below code if you take data from the Backend server.
 */
open class BaseViewModel : ViewModel() {
    open fun request(replyCount: Int, params: Params) {}
}