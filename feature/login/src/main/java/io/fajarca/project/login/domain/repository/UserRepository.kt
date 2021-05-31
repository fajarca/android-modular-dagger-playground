package io.fajarca.project.login.domain.repository

import io.fajarca.project.base.Result
import io.fajarca.project.login.domain.entity.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
}