package io.fajarca.project.user.data.repository

import io.fajarca.project.base.Either
import io.fajarca.project.user.data.mapper.UserMapper
import io.fajarca.project.user.data.mapper.UsersMapper
import io.fajarca.project.user.data.source.UserLocalDataSource
import io.fajarca.project.user.data.source.UserRemoteDataSource
import io.fajarca.project.user.domain.entity.User
import io.fajarca.project.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersMapper: UsersMapper,
    private val userMapper: UserMapper,
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUsers(): Either<Exception, List<User>> {
        val apiResult = userRemoteDataSource.getUsers()
        return when (apiResult) {
            is Either.Failure -> Either.Failure(apiResult.cause)
            is Either.Success -> Either.Success(usersMapper.map(apiResult.data))
        }
    }

    override suspend fun getUserDetail(userId : Int): Either<Exception, User> {
        val apiResult = userRemoteDataSource.getUserDetail(userId)
        return when (apiResult) {
            is Either.Failure -> Either.Failure(apiResult.cause)
            is Either.Success -> Either.Success(userMapper.map(apiResult.data))
        }
    }

    override suspend fun setPin(pin: String) {
        userLocalDataSource.setPin(pin)
    }

    override suspend fun getPin(): String {
        return userLocalDataSource.getPin()
    }


}