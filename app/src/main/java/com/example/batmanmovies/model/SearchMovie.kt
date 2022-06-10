package com.example.batmanmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchMovie(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String,
    @PrimaryKey
    val pId:Int?= null
)