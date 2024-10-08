package com.example.blueapp.domain.utils

import androidx.annotation.StringRes

sealed class Resource<out T>(
    val data: T? = null,
    val message: Int? = null,
) {
    class Success<T>(
        data: T,
    ) : Resource<T>(data)

    class Error<T>(
        @StringRes
        message: Int?,
        data: T? = null,
    ) : Resource<T>(data, message)
}
