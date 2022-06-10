package com.example.batmanmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDetail(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String?=null,
    val Country: String?=null,
    val DVD: String?=null,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String?=null,
    val Rated: String?=null,
    val Ratings: List<Rating>?=null,
    val Released: String?=null,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String?=null,
    val Writer: String,
    val Year: String,
    val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String?=null,
    @PrimaryKey
    val id: Int? = null
)