package io.fajarca.project.analytics.data.response

import com.squareup.moshi.Json

data class LogEventDto(
    @field:Json(name = "success")
    val success: Boolean? = null,
    @field:Json(name = "message")
    val message: String? = null
)