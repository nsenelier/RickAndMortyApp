package com.example.rickandmortyapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeModel(
    @Json(name = "info")
    val info: Info?,
    @Json(name = "results")
    val results: List<Episode?>?
){
    @JsonClass(generateAdapter = true)
    data class Episode(
        @Json(name = "id")
        val id: Int?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "air_date")
        val air_date: String?,
        @Json(name = "episode")
        val episode: String?,
        @Json(name = "characters")
        val character: List<String>?,
        @Json(name = "url")
        val url: String?,
        @Json(name = "created")
        val created: String?
    )
}
