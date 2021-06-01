package io.fajarca.project.user.data.mapper

import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.base.abstraction.Mapper
import io.fajarca.project.user.data.response.GetUsersDto
import io.fajarca.project.user.domain.entity.User
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