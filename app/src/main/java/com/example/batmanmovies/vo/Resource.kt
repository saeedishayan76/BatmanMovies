package com.example.batmanmovies.vo

sealed class Resource<out T>() {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
}