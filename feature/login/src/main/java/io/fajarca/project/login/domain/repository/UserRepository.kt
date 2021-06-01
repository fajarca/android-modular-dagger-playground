package io.fajarca.project.login.domain.repository

import io.fajarca.project.base.Either
import io.fajarca.project.login.domain.entity.User

interface UserRepository {
    suspend fun getUsers(): Either<List<User>>
}