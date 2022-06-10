package com.example.batmanmovies.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val Response: String,
    @SerializedName("Search")
    val movieList: List<SearchMovie>,
    val totalResults: String
)