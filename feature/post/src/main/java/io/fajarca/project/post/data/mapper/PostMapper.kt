package io.fajarca.project.post.data.mapper

import io.fajarca.project.core.abstraction.mapper.Mapper
import io.fajarca.project.post.data.response.GetPostDetailDto
import io.fajarca.project.post.domain.entity.Post
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<GetPostDetailDto, Post>() {

    override fun map(input: GetPostDetailDto): Post {
        return Post(input.id ?: 0, input.userId ?: 0, input.title ?: "", input.body ?: "")
    }

}