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

package com.goforer.profiler.data.network.response

class Resource {
    internal lateinit var status: Status

    internal var message: String? = null

    internal var data: Any? = null

    internal var errorCode: Int = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val resource = other as Resource?

        if (status !== resource?.status) {
            return false
        }

        val messageEqualed = if (message != null)
            message == resource.message
        else
            resource.message == null

        if (messageEqualed)
            return true
        else {
            val resourceEqualed = if (data != null)
                data == resource.data
            else
                resource.data == null

            return resourceEqualed
        }
    }

    override fun hashCode(): Int {
        var result = status.hashCode()

        result = 31 * result + if (message != null) message!!.hashCode() else 0
        result = 31 * result + if (data != null) data?.hashCode()!! else 0

        return result
    }

    override fun toString(): String =
        "Resource{" +
                "status=" + status +
                ", message='" + message + '\''.toString() +
                ", data=" + data +
                '}'.toString()

    internal fun success(data: Any?): Resource {
        status = Status.SUCCESS
        this.errorCode = 0
        this.data = data
        message = null

        return this
    }

    internal fun error(msg: String?, errorCode: Int): Resource {
        status = Status.ERROR
        this.errorCode = errorCode
        this.data = null
        message = msg

        return this
    }

    internal fun loading(data: Any?): Resource {
        status = Status.LOADING
        this.errorCode = 0
        @Suppress("UNCHECKED_CAST")
        this.data = data
        message = null

        return this
    }
}