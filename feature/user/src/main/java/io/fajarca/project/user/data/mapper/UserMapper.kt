package io.fajarca.project.user.data.mapper

import io.fajarca.project.base.abstraction.Mapper
import io.fajarca.project.user.data.response.GetUserDetailDto
import io.fajarca.project.user.domain.entity.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<GetUserDetailDto, User>() {

    override fun map(input: GetUserDetailDto): User {
        return User(input.id ?: 0, input.name ?: "", input.email ?: "")
    }

}