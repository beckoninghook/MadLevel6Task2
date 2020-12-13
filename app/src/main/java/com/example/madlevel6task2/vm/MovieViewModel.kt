package com.example.madlevel6task2.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch

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
                movieRepository.getMoviesFromYear(year)
            } catch (error: MovieRepository.MovieRefreshError) {
                _errorText.value = error.message
                Log.e("Trivia error", error.cause.toString())
            }
        }
    }
}