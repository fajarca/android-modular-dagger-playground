package io.fajarca.project.post.domain.usecase

import io.fajarca.project.post.domain.entity.Post
import io.fajarca.project.post.domain.repository.PostRepository
import javax.inject.Inject
import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.di.scope.FeatureScope

@FeatureScope
class GetPostsUseCase @Inject constructor(private val repository: PostRepository) :
    UseCase<UseCase.NoParams, Either<Exception, List<Post>>>() {

    override suspend fun execute(params: NoParams): Either<Exception, List<Post>> {
        return repository.getPosts()
    }

}