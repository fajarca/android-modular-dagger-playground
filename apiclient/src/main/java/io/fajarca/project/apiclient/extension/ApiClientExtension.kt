package io.fajarca.project.apiclient.extension

import io.fajarca.project.apiclient.response.ApiResponse

fun <L, R> ApiResponse<L, R>.getOrNull(): R? =
    when (this) {
        is ApiResponse.Failure -> null
        is ApiResponse.Success -> data
    }

fun <L, R> ApiResponse<L, R>.getErrorOrNull(): L? =
    when (this) {
        is ApiResponse.Failure -> cause
        is ApiResponse.Success -> null
    }
