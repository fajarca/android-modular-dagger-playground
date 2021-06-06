package io.fajarca.project.post.data.source

import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.ApiClient
import io.fajarca.project.post.data.response.GetPostDetailDto
import io.fajarca.project.post.data.response.GetPostsDto
import io.fajarca.project.post.data.service.PostService
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient,
    private val postService: PostService
) {

    suspend fun getPost(): Either<Exception, List<GetPostsDto>> {
        return apiClient.call { postService.getPosts() }
    }

    suspend fun getPostDetail(postId : Int): Either<Exception, GetPostDetailDto> {
        return apiClient.call { postService.getPostDetail(postId) }
    }

}