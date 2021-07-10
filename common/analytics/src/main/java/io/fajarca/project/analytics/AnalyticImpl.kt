package io.fajarca.project.analytics

import io.fajarca.project.analytics.data.service.AnalyticService
import javax.inject.Inject


class AnalyticImpl @Inject constructor(
    private val analyticService: AnalyticService
) : Analytic {

    override suspend fun logEvent(key: String, value: Any): Boolean {
    /*    val request = LogEventRequest(key, value.toString())
        return apiClient.call { analyticService.logEvent(request) }*/


        return true
    }

}