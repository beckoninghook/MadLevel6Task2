package com.example.madlevel6task2.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.api.MovieApi
import com.example.madlevel6task2.api.MovieApiService
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class MovieViewModel (application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository()
    val movies = movieRepository.movies
    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from view for error showing
     * Encapsulation :)
     */
    val errorText: LiveData<String>
        get() = _errorText
    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getMoviesFromYear(year : Int) {

        viewModelScope.launch {
            try {
                //the triviaRepository sets it's own livedata property
                //our own trivia LiveData property points to te one in that repository
                //timeout the request after 5 seconds
            movieRepository.getMoviesFromYear(year)
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                Log.e("Movie error", error.cause.toString())
            }
        }
    }
}