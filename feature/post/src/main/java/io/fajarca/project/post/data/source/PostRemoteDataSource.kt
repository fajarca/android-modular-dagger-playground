package io.fajarca.project.post.data.source

import io.fajarca.project.base.Either
import io.fajarca.project.apiclient.ApiClient
import io.fajarca.project.apiclient.ApiClientImpl
import io.fajarca.project.apiclient.ApiResult
import io.fajarca.project.post.data.response.GetPostDetailDto
import io.fajarca.project.post.data.response.GetPostsDto
import io.fajarca.project.post.data.service.PostService
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val postService: PostService
) {

    suspend fun getPost(): Either<Exception, List<GetPostsDto>> {
        val apiClient = ApiClientImpl()
        val response = apiClient.call { postService.getPosts() }
        return when(response) {
            is ApiResult.Success -> Either.Success(response.data)
            is ApiResult.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun getPostDetail(postId : Int): Either<Exception, GetPostDetailDto> {
        val apiClient = ApiClientImpl()
        val response = apiClient.call { postService.getPostDetail(postId) }
        return when(response) {
            is ApiResult.Success -> Either.Success(response.data)
            is ApiResult.Failure -> Either.Failure(response.cause)
        }
    }

}