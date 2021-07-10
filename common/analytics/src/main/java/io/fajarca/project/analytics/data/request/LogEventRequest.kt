package io.fajarca.project.analytics.data.request

import com.squareup.moshi.Json

data class LogEventRequest(
    @field:Json(name = "key")
    val key: String? = null,
    @field:Json(name = "value")
    val value: String? = null
)