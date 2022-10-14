package com.vintech.visprog_331.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vintech.visprog_331.adapter.NowPlayingAdapter
import com.vintech.visprog_331.adapter.NowPlayingAdapter2
import com.vintech.visprog_331.databinding.ActivityMainBinding
import com.vintech.visprog_331.helper.Const
import com.vintech.visprog_331.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NowPlayingAdapter2
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvMain.visibility = View.INVISIBLE

        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        viewModel.getNowPlaying(Const.API_KEY, "en-US", 1)

        viewModel.nowPlaying.observe(this) { response ->

            if (response != null){
                binding.progressBar2.visibility = View.INVISIBLE
                binding.rvMain.visibility = View.VISIBLE
            }

            binding.rvMain.layoutManager = GridLayoutManager(this, 2)
            adapter = NowPlayingAdapter2(response)
            binding.rvMain.adapter = adapter
        }
    }
}