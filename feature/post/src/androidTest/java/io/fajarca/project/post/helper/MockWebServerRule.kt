package io.fajarca.project.post.helper

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher() {
    lateinit var server: MockWebServer

    companion object {
        private const val WEB_SERVER_PORT = 8080
    }

    override fun starting(description: Description?) {
        server = MockWebServer()
        server.start(WEB_SERVER_PORT)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        server.shutdown()
        super.finished(description)
    }
}
