package com.example.batmanmovies.repository.local

import androidx.room.TypeConverter
import com.example.batmanmovies.model.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun fromItemList(itemList: List<Rating>?): String? {
        val type = object : TypeToken<List<Rating>>() {}.type
        return gson.toJson(itemList, type)
    }

    @TypeConverter
    fun toItemList(itemList: String?): List<Rating>? {
        val type = object : TypeToken<List<Rating>>() {}.type
        return gson.fromJson(itemList, type)
    }

}