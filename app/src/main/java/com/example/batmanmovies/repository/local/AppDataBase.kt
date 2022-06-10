package com.example.batmanmovies.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.batmanmovies.model.MovieDetail
import com.example.batmanmovies.model.SearchMovie

@Database(
    entities = [
        SearchMovie::class,
        MovieDetail::class
    ],
    version = 5, exportSchema = true
)
@TypeConverters(MovieTypeConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun moviesData(): MovieDao
    abstract fun movieDetailDao():MovieDetailDao
}