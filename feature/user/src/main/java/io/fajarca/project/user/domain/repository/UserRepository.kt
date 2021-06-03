package io.fajarca.project.user.domain.repository

import io.fajarca.project.base.Either
import io.fajarca.project.user.domain.entity.User

interface UserRepository {
    suspend fun getUsers(): Either<Exception, List<User>>
}