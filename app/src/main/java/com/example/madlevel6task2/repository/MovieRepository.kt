package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.api.MovieApi
import com.example.madlevel6task2.api.MovieApiService
import com.example.madlevel6task2.model.Movie
import com.google.gson.Gson
import kotlinx.coroutines.withTimeout

class MovieRepository {


    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val movies: LiveData<List<Movie>>
        get() = _movies


    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

    suspend fun getMoviesFromYear(year : Int){
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                val filter = HashMap<String , String>()
                filter["api_key"] = "1f14156d794b279bdd31b16eba76a4e6"
                filter["sort_by"] = "popularity.desc"
                filter["year"]=year.toString()
                movieApiService.getMovies(filter)
            }

                _movies.value = result.results;
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh Movies", error)
        }
    }

    suspend fun getMovieDetails(){

    }
}