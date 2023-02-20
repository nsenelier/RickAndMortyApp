package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.util.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject
import javax.inject.Singleton

interface Repository{
    suspend fun getAllCharacters()
    suspend fun getAllLocations()
    suspend fun getAllEpisodes()
    val rmData: StateFlow<UIState>
}

@Singleton
class RepositoryImpl @Inject constructor(

    private val serviceApi: RickMortyApi
): Repository{
    private val _rmData = MutableStateFlow<UIState>(UIState.Empty)
    override val rmData: StateFlow<UIState>
        get() = _rmData

    override suspend fun getAllCharacters() {
        _rmData.value = UIState.Loading
        try {
            val response = serviceApi.getCharacter()
            if (response.isSuccessful) {
                response.body()?.let {
                    _rmData.value = UIState.Success(it)
                } ?: throw Exception("Character response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            _rmData.value = UIState.Failure(e)
        }

    }

    override suspend fun getAllLocations() {
        _rmData.value = UIState.Loading

        try {
            val response = serviceApi.getLocation()
            if (response.isSuccessful) {
                response.body()?.let {
                    _rmData.value = UIState.Success(it)
                } ?: throw Exception("Location response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            _rmData.value = UIState.Failure(e)
        }
    }

    override suspend fun getAllEpisodes(){
        _rmData.value = UIState.Loading
        try {
            val response = serviceApi.getEpisode()
            if (response.isSuccessful) {
                response.body()?.let {
                    _rmData.value = UIState.Success(it)
                } ?: throw Exception("Episode response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            _rmData.value = UIState.Failure(e)
        }
    }

}