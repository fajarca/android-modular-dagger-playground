package io.fajarca.project.login.data.mapper

import io.fajarca.project.base.ModuleScope
import io.fajarca.project.base.abstraction.Mapper
import io.fajarca.project.login.data.response.GetUsersDto
import io.fajarca.project.login.domain.entity.User
import javax.inject.Inject

@ModuleScope
class UserMapper @Inject constructor() : Mapper<List<GetUsersDto>, List<User>>() {

    override fun map(input: List<GetUsersDto>): List<User> {
        val users = mutableListOf<User>()
        for (user in input) {
            users.add(User(user.id ?: 0, user.name ?: "", user.email ?: ""))
        }
        return users
    }

}