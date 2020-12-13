package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Movie (
    @SerializedName("backdrop_path") var backdrop_path: String,
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var type: Date,
    @SerializedName("vote_average") var vote_average: Double,
    @SerializedName("overview") var overview: String,
    @SerializedName("id") val id : Int


) : Serializable {

    fun getImageUrl() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/$poster_path"
}