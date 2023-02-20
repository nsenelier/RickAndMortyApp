package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.model.CharacterModel
import com.example.rickandmortyapp.data.model.EpisodeModel
import com.example.rickandmortyapp.data.model.LocationModel
import com.example.rickandmortyapp.util.ENDPOINT_CHARACTER
import com.example.rickandmortyapp.util.ENDPOINT_EPISODE
import com.example.rickandmortyapp.util.ENDPOINT_LOCATION
import retrofit2.Response
import retrofit2.http.GET

interface RickMortyApi {
    @GET(ENDPOINT_CHARACTER)
    suspend fun getCharacter(): Response<CharacterModel>

    @GET(ENDPOINT_LOCATION)
    suspend fun getLocation(): Response<LocationModel>

    @GET(ENDPOINT_EPISODE)
    suspend fun getEpisode(): Response<EpisodeModel>
}