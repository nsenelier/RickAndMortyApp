package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.CharacterModel
import com.example.rickandmortyapp.data.model.EpisodeModel
import com.example.rickandmortyapp.data.model.LocationModel
import com.example.rickandmortyapp.data.remote.RickMortyApi
import com.example.rickandmortyapp.util.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

import javax.inject.Inject
import javax.inject.Singleton

interface Repository{
    suspend fun getAllCharacters(): Response<CharacterModel>
    suspend fun getAllLocations(): Response<LocationModel>
    suspend fun getAllEpisodes(): Response<EpisodeModel>
    val rmData: StateFlow<UIState>
}

@Singleton
class RepositoryImpl @Inject constructor(
    private val serviceApi: RickMortyApi
): Repository {
    private val _rmData = MutableStateFlow<UIState>(UIState.Empty)
     override val rmData: StateFlow<UIState>
     get() = _rmData

    override suspend fun getAllCharacters(): Response<CharacterModel>{
        _rmData.value = UIState.Loading
        val response = serviceApi.getCharacter()
        try {

            if (response.isSuccessful) {
                response.body()?.let {
                    _rmData.value = UIState.Success(it.results)

                } ?: throw Exception("Character response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }

        } catch (e: Exception) {
            _rmData.value = UIState.Failure(e.toString())
        }
        return response
    }

    override suspend fun getAllLocations(): Response<LocationModel> {
        _rmData.value = UIState.Loading
        val response = serviceApi.getLocation()
        try {

            if (response.isSuccessful) {
                response.body()?.let {
                    _rmData.value = UIState.Success(it.results)
                } ?: throw Exception("Location response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            _rmData.value = UIState.Failure(e.toString())
        }
        return response
    }

    override suspend fun getAllEpisodes(): Response<EpisodeModel>{
        _rmData.value = UIState.Loading
        val response = serviceApi.getEpisode()
        try {

            if (response.isSuccessful) {
                response.body()?.let {
                    _rmData.value = UIState.Success(it.results)
                } ?: throw Exception("Episode response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            _rmData.value = UIState.Failure(e.toString())
        }
        return response
    }

}