package com.example.madlevel6task2.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task2.R
import com.example.madlevel6task2.adapter.MovieAdapter
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.vm.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_select_year.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAddReminderResult()
    }



    private fun observeAddReminderResult() {
        setFragmentResultListener(REQ_MOVIE_KEY) { key, bundle ->
            bundle.getSerializable(BUNDLE_MOVIE_KEY)?.let {
                initView(it as Movie);


            } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!")

        }
    }

    private fun initView(movie: Movie){
        println(movie)
        tvTitle.text = movie.title;
        tvReleaseDate.text = movie.releaseDate.toString()
        tvRating.text = movie.vote_average.toString()
        tvRecievedOverview.text = movie.overview
        Glide.with(requireContext()).load(movie.getImageUrl()).into(ivPoster)
        Glide.with(requireContext()).load(movie.getImageBackdropUrl()).into(ivBackDrop)

    }

}