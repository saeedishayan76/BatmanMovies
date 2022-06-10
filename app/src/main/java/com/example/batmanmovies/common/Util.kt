package com.example.batmanmovies.common

import timber.log.Timber

class Util {
    companion object{
        fun convertMovieRunTimeToHourMinutes(runTime:String):String
        {
            var time = runTime.replace("min","").trim()
            val hour = time.toInt()/60
            val minutes = time.toInt() % 60
            return "${hour}h ${minutes}m"
        }
    }
}