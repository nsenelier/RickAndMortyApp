package com.example.rickandmortyapp.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterModel(
    @Json(name = "info")
    val info: Info?,
    @Json(name = "results")
    val results: List<Character>
) {

    @JsonClass(generateAdapter = true)
    data class Character(
        @Json(name = "created")
        val created: String?,
        @Json(name = "episode")
        val episode: List<String?>?,
        @Json(name = "gender")
        val gender: String?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "image")
        val image: String?,
        @Json(name = "location")
        val location: Location?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "origin")
        val origin: Origin?,
        @Json(name = "species")
        val species: String?,
        @Json(name = "status")
        val status: String?,
        @Json(name = "type")
        val type: String?,
        @Json(name = "url")
        val url: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Location(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )

        @JsonClass(generateAdapter = true)
        data class Origin(
            @Json(name = "name")
            val name: String?,
            @Json(name = "url")
            val url: String?
        )
    }

}