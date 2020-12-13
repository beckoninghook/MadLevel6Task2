package com.example.madlevel6task2.api

import com.example.madlevel6task2.model.MovieRes

import retrofit2.http.GET

import retrofit2.http.QueryMap

interface MovieApiService {

    @GET("movie")
    suspend fun getMovies(@QueryMap filter: HashMap<String, String>): MovieRes

}
