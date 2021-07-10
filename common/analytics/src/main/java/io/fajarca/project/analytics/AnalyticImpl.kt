package io.fajarca.project.analytics

import io.fajarca.project.analytics.data.request.LogEventRequest
import io.fajarca.project.analytics.data.response.LogEventDto
import io.fajarca.project.analytics.data.service.AnalyticService
import io.fajarca.project.apiclient.ApiClient
import io.fajarca.project.apiclient.response.ApiResponse
import javax.inject.Inject


class AnalyticImpl @Inject constructor(
    private val analyticService: AnalyticService,
    private val apiClient: ApiClient
) : Analytic {

    override suspend fun logEvent(key: String, value: Any): ApiResponse<Exception, LogEventDto> {
        val request = LogEventRequest(key, value.toString())
        return apiClient.call { analyticService.logEvent(request) }
    }

}