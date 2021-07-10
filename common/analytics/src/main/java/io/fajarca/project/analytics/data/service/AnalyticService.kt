package io.fajarca.project.analytics.data.service

import io.fajarca.project.analytics.data.request.LogEventRequest
import io.fajarca.project.analytics.data.response.LogEventDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AnalyticService {
    @POST("event")
    suspend fun logEvent(@Body request : LogEventRequest) : Response<LogEventDto>
}