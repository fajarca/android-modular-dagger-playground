package io.fajarca.project.post.data.service

import io.fajarca.project.post.data.response.GetPostDetailDto
import io.fajarca.project.post.data.response.GetPostsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    suspend fun getPosts() : Response<List<GetPostsDto>>

    @GET("posts/{postId}")
    suspend fun getPostDetail(@Path("postId") userId : Int) : Response<GetPostDetailDto>
}