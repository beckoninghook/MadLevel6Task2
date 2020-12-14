package com.example.madlevel6task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task2.R
import com.example.madlevel6task2.adapter.MovieAdapter
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.vm.MovieViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_select_year.*
const val REQ_MOVIE_KEY = "req_movie"
const val BUNDLE_MOVIE_KEY = "bundle_movie"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SelectYearFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModels()


    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies, ::onMovieClick)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_year, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            viewModel.getMoviesFromYear(etYear.text.toString().toInt())
        }

        initViews()

        observeMovies()
    }

    private fun initViews(){
        rvMovies.layoutManager = GridLayoutManager(activity , 2)
        rvMovies.adapter = movieAdapter
        rvMovies.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun onMovieClick(movie: Movie) {
        setFragmentResult(REQ_MOVIE_KEY, bundleOf(Pair(BUNDLE_MOVIE_KEY, movie)))
        Snackbar.make(rvMovies, "This color is: ${movie.title}", Snackbar.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }
}