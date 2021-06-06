package io.fajarca.project.post.domain.repository

import io.fajarca.project.base.Either
import io.fajarca.project.post.domain.entity.Post

interface PostRepository {
    suspend fun getPosts(): Either<Exception, List<Post>>
    suspend fun getPostDetail(postId : Int): Either<Exception, Post>
}