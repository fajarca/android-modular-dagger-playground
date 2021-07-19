package io.fajarca.project.test_shared.webserver

import android.net.Uri
import io.fajarca.project.test_shared.helper.FileUtils
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerDispatcher {

    inner class SuccessDispatcher(private val responseCode: Int) : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            val url = request.path ?: return MockResponse().setResponseCode(404)
            val endpoint = Uri.parse(url).lastPathSegment
            val body = FileUtils.readTestResourceFile("json/$endpoint.json")

            return MockResponse()
                .setResponseCode(responseCode)
                .setBody(body)
        }
    }

    inner class ErrorDispatcher(private val responseCode: Int) : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse()
                .setResponseCode(responseCode)
        }
    }
}
