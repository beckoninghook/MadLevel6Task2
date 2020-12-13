package com.example.madlevel6task2.api

import com.example.madlevel6task2.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    // The GET method needed to retrieve a random number trivia.
    @GET("&year={year}")
    suspend fun getMovie(@Path("year") year: Int?): Movie
}
