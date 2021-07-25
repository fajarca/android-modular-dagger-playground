package io.fajarca.project.apiclient

import io.fajarca.project.apiclient.exception.ClientErrorException
import io.fajarca.project.apiclient.exception.NoInternetConnection
import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.apiclient.exception.TimeoutException
import io.fajarca.project.apiclient.exception.UnknownNetworkErrorException
import io.fajarca.project.apiclient.extension.getErrorOrNull
import io.fajarca.project.apiclient.extension.getOrNull
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class ApiClientImplTest {

    private val apiClient = ApiClientImpl()
    private val responseJson = """
        [{
        	"id": 1296269,
        	"node_id": "MDEwOlJlcG9zaXRvcnkxMjk2MjY5",
        	"name": "Hello-World",
        	"full_name": "octocat/Hello-World",
        	"private": false,
        	"html_url": "https://github.com/octocat/Hello-World",
        	"description": "This your first repo!",
        	"fork": false,
        	"languages_url": "http://api.github.com/repos/octocat/Hello-World/languages",
        	"stargazers_count": 80,
        	"watchers_count": 80,
        	"pushed_at": "2011-01-26T19:06:43Z",
        	"created_at": "2011-01-26T19:01:12Z",
        	"updated_at": "2011-01-26T19:14:43Z",
        	"subscribers_count": 42
        }]
    """.trimIndent()

    @Test
    fun whenResponseCodeIsServerErrorShouldMapToServerErrorException() = runBlocking {
        val expected = ServerErrorException(500)

        val exception: Response<HttpException> = Response.error(
            500,
            responseJson.toResponseBody("application/json".toMediaTypeOrNull())
        )

        val actual = apiClient.call { throw HttpException(exception) }.getErrorOrNull()

        assertEquals(expected, actual)
    }


    @Test
    fun whenResponseCodeIsClientErrorShouldMapToClientErrorException() = runBlocking {
        val expected = ClientErrorException(404)

        val exception: Response<HttpException> = Response.error(
            404,
            responseJson.toResponseBody("application/json".toMediaTypeOrNull())
        )

        val actual = apiClient.call { throw HttpException(exception) }.getErrorOrNull()


        assertEquals(expected, actual)
    }

    @Test
    fun whenResponseCodeIsInvalidShouldMapToUnknownNetworkErrorException() = runBlocking {
        val expected = UnknownNetworkErrorException("Response code 600 is invalid")

        val exception: Response<HttpException> = Response.error(
            600,
            responseJson.toResponseBody("application/json".toMediaTypeOrNull())
        )

        val actual = apiClient.call { throw HttpException(exception) }.getErrorOrNull()


        assertEquals(expected, actual)
    }

    @Test
    fun whenResponseIsSuccessShouldReturnData() = runBlocking {
        val expected = "Some data"
        val actual = apiClient.call { "Some data" }.getOrNull()
        assertEquals(expected, actual)
    }

    @Test
    fun whenRequestIsTimeoutShouldMapToTimeoutException() = runBlocking {
        val actual = apiClient.call { throw SocketTimeoutException() }.getErrorOrNull()
        assert(actual is TimeoutException)
    }

    @Test
    fun whenNoInternetConnectionShouldMapToNoInternetConnectionException() = runBlocking {
        val actual = apiClient.call { throw UnknownHostException() }.getErrorOrNull()
        assert(actual is NoInternetConnection)
    }

    @Test
    fun whenUndefinedExceptionOccurredShouldMapToUnknownException() = runBlocking {
        val actual = apiClient.call { throw IllegalArgumentException() }.getErrorOrNull()
        assertEquals(UnknownNetworkErrorException("Undefined error"), actual)
    }
}