package io.fajarca.project.apiclient.extension

import io.fajarca.project.apiclient.response.ApiResponse

fun <L, R> ApiResponse<L, R>.getOrElse(value: R): R =
    when (this) {
        is ApiResponse.Failure -> value
        is ApiResponse.Success -> data
    }

fun <L, R> ApiResponse<L, R>.getErrorOrElse(value: L): L =
    when (this) {
        is ApiResponse.Failure -> cause
        is ApiResponse.Success -> value
    }
