package com.vintech.visprog_331.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vintech.visprog_331.adapter.CompanyCollectionAdapter
import com.vintech.visprog_331.adapter.CountryAdapter
import com.vintech.visprog_331.adapter.GenreCollectionAdapter
import com.vintech.visprog_331.adapter.LanguageAdapter
import com.vintech.visprog_331.databinding.ActivityMovieDetailBinding
import com.vintech.visprog_331.helper.Const
import com.vintech.visprog_331.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapterGenre: GenreCollectionAdapter
    private lateinit var adapterLanguage: LanguageAdapter
    private lateinit var adapterCountry: CountryAdapter
    private lateinit var adapterCompany: CompanyCollectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGenre.visibility = View.INVISIBLE
        binding.rvLanguage.visibility = View.INVISIBLE
        binding.rvProductionCompany.visibility = View.INVISIBLE
        binding.rvProductionCountry.visibility = View.INVISIBLE
        binding.tvOverview.visibility = View.INVISIBLE
        binding.tvTitleMovieDetail.visibility = View.INVISIBLE
        binding.textView.visibility = View.INVISIBLE
        binding.textView2.visibility = View.INVISIBLE
        binding.textView3.visibility = View.INVISIBLE
        binding.textView4.visibility = View.INVISIBLE
        binding.textView5.visibility = View.INVISIBLE
        binding.imgPosterMovieDetail.visibility = View.INVISIBLE

        val movieId = intent.getIntExtra("movie_id", 0)
        Toast.makeText(applicationContext, "Movie ID: $movieId", Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovieDetail(Const.API_KEY, movieId)
        viewModel.movieDetail.observe(this, Observer { response ->


            if (response != null) {
                binding.progressBar.visibility = View.INVISIBLE
                binding.rvGenre.visibility = View.VISIBLE
                binding.rvLanguage.visibility = View.VISIBLE
                binding.rvProductionCompany.visibility = View.VISIBLE
                binding.rvProductionCountry.visibility = View.VISIBLE
                binding.tvOverview.visibility = View.VISIBLE
                binding.tvTitleMovieDetail.visibility = View.VISIBLE
                binding.textView.visibility = View.VISIBLE
                binding.textView2.visibility = View.VISIBLE
                binding.textView3.visibility = View.VISIBLE
                binding.textView4.visibility = View.VISIBLE
                binding.textView5.visibility = View.VISIBLE
                binding.imgPosterMovieDetail.visibility = View.VISIBLE
            }


            binding.tvTitleMovieDetail.apply {
                text = response.title
            }

            binding.tvOverview.apply {
                text = response.overview
            }

            binding.rvGenre.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterGenre = GenreCollectionAdapter(response.genres)
            binding.rvGenre.adapter = adapterGenre

            binding.rvLanguage.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterLanguage = LanguageAdapter(response.spoken_languages)
            binding.rvLanguage.adapter = adapterLanguage

            binding.rvProductionCountry.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterCountry = CountryAdapter(response.production_countries)
            binding.rvProductionCountry.adapter = adapterCountry

            binding.rvProductionCompany.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            adapterCompany = CompanyCollectionAdapter(response.production_companies)
            binding.rvProductionCompany.adapter = adapterCompany

            Glide.with(applicationContext).load(Const.IMG_URL + response.backdrop_path)
                .into(binding.imgPosterMovieDetail)
        })


    }
}