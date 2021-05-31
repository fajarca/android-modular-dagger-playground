package io.fajarca.project.login.data

import com.squareup.moshi.Json


data class GetUsersDto(
    @field:Json(name = "address")
    val address: Address? = null,
    @field:Json(name = "company")
    val company: Company? = null,
    @field:Json(name = "email")
    val email: String? = null,
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "phone")
    val phone: String? = null,
    @field:Json(name = "username")
    val username: String? = null,
    @field:Json(name = "website")
    val website: String? = null
) {
    data class Address(
        @field:Json(name = "city")
        val city: String? = null,
        @field:Json(name = "geo")
        val geo: Geo? = null,
        @field:Json(name = "street")
        val street: String? = null,
        @field:Json(name = "suite")
        val suite: String? = null,
        @field:Json(name = "zipcode")
        val zipcode: String? = null
    ) {
        data class Geo(
            @field:Json(name = "lat")
            val lat: String? = null,
            @field:Json(name = "lng")
            val lng: String? = null
        )
    }

    data class Company(
        @field:Json(name = "bs")
        val bs: String? = null,
        @field:Json(name = "catchPhrase")
        val catchPhrase: String? = null,
        @field:Json(name = "name")
        val name: String? = null
    )
}
