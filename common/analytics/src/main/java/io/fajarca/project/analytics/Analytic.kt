package io.fajarca.project.analytics

import io.fajarca.project.analytics.data.response.LogEventDto
import io.fajarca.project.apiclient.response.ApiResponse

interface Analytic {
    suspend fun logEvent(key: String, value: Any): ApiResponse<Exception, LogEventDto>
}