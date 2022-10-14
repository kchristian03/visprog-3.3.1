package com.vintech.visprog_331.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vintech.visprog_331.model.MovieDetails
import com.vintech.visprog_331.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.vintech.visprog_331.model.Result
import com.vintech.visprog_331.view.MovieDetail
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) :
    ViewModel() {

    //get now playing data
    val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }
    val nowPlaying: LiveData<ArrayList<Result>>
        get() = _nowPlaying

    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingData(apiKey, language, page).let { response ->
            if (response.isSuccessful) {
                _nowPlaying.postValue(response.body()?.results as ArrayList<Result>?)
            } else {
                Log.e("Retrieve Data", "Failed!")
            }
        }
    }

    //get movie detail
    val _movieDetail: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }
    val movieDetail: LiveData<MovieDetails>
        get() = _movieDetail

    fun getMovieDetail(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailResult(apiKey, movieId).let { response ->
            if (response.isSuccessful) {
                _movieDetail.postValue(response.body() as MovieDetails)
            } else {
                Log.e("Get Movie Details Data", "Failed!")
            }
        }
    }
}