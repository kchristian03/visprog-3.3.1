package com.vintech.visprog_331.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vintech.visprog_331.repository.NowPlayingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.vintech.visprog_331.model.Result
import kotlinx.coroutines.launch

@HiltViewModel
class NowPlayingViewModel @Inject constructor(private val repository: NowPlayingRepository): ViewModel(){
    val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }

    val nowPlaying: LiveData<ArrayList<Result>>
    get() = _nowPlaying

    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingData(apiKey, language, page).let {
            response ->
            if (response.isSuccessful){
                _nowPlaying.postValue(response.body()?.results as ArrayList<Result>?)
            }else{
                Log.e("Retrieve Data", "Failed!")
            }
        }
    }
}