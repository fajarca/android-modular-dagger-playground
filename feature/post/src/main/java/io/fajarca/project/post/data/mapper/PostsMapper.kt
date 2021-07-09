package io.fajarca.project.post.data.mapper

import io.fajarca.project.base.abstraction.mapper.Mapper
import io.fajarca.project.post.data.response.GetPostsDto
import io.fajarca.project.post.domain.entity.Post
import javax.inject.Inject

class PostsMapper @Inject constructor() : Mapper<List<GetPostsDto>, List<Post>>() {

    override fun map(input: List<GetPostsDto>): List<Post> {
        val posts = mutableListOf<Post>()
        for (post in input) {
            posts.add(Post(post.id ?: 0, post.userId ?: 0, post.title ?: "", post.body ?: ""))
        }
        return posts
    }

}