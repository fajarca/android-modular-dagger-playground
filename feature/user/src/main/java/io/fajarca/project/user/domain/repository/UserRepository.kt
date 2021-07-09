package io.fajarca.project.user.domain.repository

import io.fajarca.project.core.Either
import io.fajarca.project.user.domain.entity.User

interface UserRepository {
    suspend fun getUsers(): Either<Exception, List<User>>
    suspend fun getUserDetail(userId: Int): Either<Exception, User>
    fun setPin(pin: String)
    fun getPin(): String
}