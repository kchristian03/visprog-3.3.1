package com.vintech.visprog_331.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.vintech.visprog_331.databinding.ActivityMovieDetailBinding
import com.vintech.visprog_331.helper.Const
import com.vintech.visprog_331.model.MovieDetails
import com.vintech.visprog_331.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra("movie_id", 0)
        Toast.makeText(applicationContext, "Movie ID: $movieId", Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovieDetail(Const.API_KEY, movieId)
        viewModel.movieDetail.observe(this, Observer{
            response->
            binding.tvTitleMovieDetail.apply {
                text = response.title
            }

            Glide.with(applicationContext).load(Const.IMG_URL + response.backdrop_path).into(binding.imgPosterMovieDetail)
        })
    }
}