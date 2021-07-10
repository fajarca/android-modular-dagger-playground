package io.fajarca.project.post.data.repository

import io.fajarca.project.core.Either
import io.fajarca.project.post.data.mapper.PostMapper
import io.fajarca.project.post.data.mapper.PostsMapper
import io.fajarca.project.post.data.source.PostRemoteDataSource
import io.fajarca.project.post.domain.entity.Post
import io.fajarca.project.post.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postsMapper: PostsMapper,
    private val postMapper: PostMapper,
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {

    override suspend fun getPosts(): Either<Exception, List<Post>> {
        val apiResult = postRemoteDataSource.getPost()
        return when (apiResult) {
            is Either.Left -> Either.Left(apiResult.cause)
            is Either.Right -> Either.Right(postsMapper.map(apiResult.data))
        }
    }

    override suspend fun getPostDetail(postId : Int): Either<Exception, Post> {
        val apiResult = postRemoteDataSource.getPostDetail(postId)
        return when (apiResult) {
            is Either.Left -> Either.Left(apiResult.cause)
            is Either.Right -> Either.Right(postMapper.map(apiResult.data))
        }
    }

}