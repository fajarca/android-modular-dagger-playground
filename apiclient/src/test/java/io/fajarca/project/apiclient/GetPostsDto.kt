package io.fajarca.project.apiclient


import com.squareup.moshi.Json


data class GetPostsDto(
    @field:Json(name = "body")
    val body: String? = null,
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "title")
    val title: String? = null,
    @field:Json(name = "userId")
    val userId: Int? = null
)
