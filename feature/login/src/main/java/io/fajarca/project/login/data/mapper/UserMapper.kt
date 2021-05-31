package io.fajarca.project.login.data.mapper

import io.fajarca.project.base.abstraction.Mapper
import io.fajarca.project.login.data.GetUsersDto
import io.fajarca.project.login.domain.entity.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<GetUsersDto, List<User>>() {

    override fun map(input: GetUsersDto): List<User> {
        val users = mutableListOf<User>()
        for (user in input) {
            users.add(User(user.id ?: 0, user.name ?: "", user.email ?: ""))
        }
        return users
    }

}