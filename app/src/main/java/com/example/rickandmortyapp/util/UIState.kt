package com.example.rickandmortyapp.util

sealed class UIState{
    object Loading: UIState()
    class Failure(val errorMessage: String): UIState()
    data class Success<T> (val response: T): UIState()
    object Empty: UIState()
}