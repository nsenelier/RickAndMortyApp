package com.example.rickandmortyapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationModel(
    @Json(name = "info")
    val info: Info?,
    @Json(name = "results")
    val results: List<Location?>?
){
    @JsonClass(generateAdapter = true)
    data class Location(
        @Json(name= "id")
        val id: Int?,
        @Json(name= "name")
        val name: String?,
        @Json(name= "type")
        val type: String?,
        @Json(name= "dimension")
        val dimension: String?,
        @Json(name= "residents")
        val residents: List<String?>?,
        @Json(name= "url")
        val url: String?,
        @Json(name= "created")
        val created: String?
    )
}
