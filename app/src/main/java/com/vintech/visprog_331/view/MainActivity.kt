package com.vintech.visprog_331.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vintech.visprog_331.R
import com.vintech.visprog_331.adapter.NowPlayingAdapter
import com.vintech.visprog_331.databinding.ActivityMainBinding
import com.vintech.visprog_331.helper.Const
import com.vintech.visprog_331.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NowPlayingAdapter
    private lateinit var viewModel: NowPlayingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        viewModel.getNowPlaying(Const.API_KEY, "en-US", 1)

        viewModel.nowPlaying.observe(this) { response ->
            binding.rvMain.layoutManager = LinearLayoutManager(this)
            adapter = NowPlayingAdapter(response)
            binding.rvMain.adapter = adapter
        }
    }
}