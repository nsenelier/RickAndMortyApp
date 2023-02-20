package com.example.rickandmortyapp.util

sealed class UIState {
    object Loading: UIState()
    class Failure(val errorMessage: Exception): UIState()
    data class Success<T: Any> (val response: T): UIState()
    object Empty: UIState()

}