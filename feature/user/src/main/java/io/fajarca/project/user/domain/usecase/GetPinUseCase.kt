package io.fajarca.project.user.domain.usecase

import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.user.domain.repository.UserRepository
import javax.inject.Inject

@FeatureScope
class GetPinUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<UseCase.NoParams, String>() {

    override suspend fun execute(params: NoParams): String {
        return repository.getPin()
    }

}