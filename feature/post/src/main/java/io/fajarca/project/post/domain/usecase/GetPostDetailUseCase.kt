package io.fajarca.project.post.domain.usecase

import io.fajarca.project.post.domain.entity.Post
import io.fajarca.project.post.domain.repository.PostRepository
import javax.inject.Inject
import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.usecase.UseCase

class GetPostDetailUseCase @Inject constructor(private val repository: PostRepository) :
    UseCase<Int, Either<Exception, Post>>() {

    override suspend fun execute(params: Int): Either<Exception, Post> {
        return repository.getPostDetail(params)
    }

}