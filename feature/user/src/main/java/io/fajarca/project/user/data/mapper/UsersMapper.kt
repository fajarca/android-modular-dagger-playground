package io.fajarca.project.user.data.mapper

import io.fajarca.project.base.abstraction.mapper.Mapper
import io.fajarca.project.user.data.response.GetUsersDto
import io.fajarca.project.user.domain.entity.User
import javax.inject.Inject

class UsersMapper @Inject constructor() : Mapper<List<GetUsersDto>, List<User>>() {

    override fun map(input: List<GetUsersDto>): List<User> {
        val users = mutableListOf<User>()
        for (user in input) {
            users.add(User(user.id ?: 0, user.name ?: "", user.email ?: ""))
        }
        return users
    }

}