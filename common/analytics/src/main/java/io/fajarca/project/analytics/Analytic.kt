package io.fajarca.project.analytics

interface Analytic {
    suspend fun logEvent(key: String, value: Any): Boolean
}