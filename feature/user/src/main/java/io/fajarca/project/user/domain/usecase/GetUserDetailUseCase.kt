package io.fajarca.project.user.domain.usecase

import io.fajarca.project.user.domain.entity.User
import io.fajarca.project.user.domain.repository.UserRepository
import javax.inject.Inject
import io.fajarca.project.base.Either
import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.di.scope.FeatureScope

@FeatureScope
class GetUserDetailUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<Int, Either<Exception, User>>() {

    override suspend fun execute(params: Int): Either<Exception, User> {
        return repository.getUserDetail(params)
    }

}