package com.vintech.visprog_331.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vintech.visprog_331.R
import com.vintech.visprog_331.model.Result
import com.vintech.visprog_331.view.MovieDetail

class NowPlayingAdapter(private val dataSet: ArrayList<Result>) :
    RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvReleased: TextView
        val cvNowPlaying: CardView

        init {
            // Define click listener for the ViewHolder's View.
            tvTitle = view.findViewById(R.id.tv_title_nowPlaying)
            tvReleased = view.findViewById(R.id.tv_realsed_nowPlaying)
            cvNowPlaying = view.findViewById((R.id.cv_nowPlaying))
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_now_playing, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = dataSet[position].title
        viewHolder.tvReleased.text = dataSet[position].release_date
        viewHolder.cvNowPlaying.setOnClickListener {
            val intent = Intent(it.context, MovieDetail::class.java)
            intent.putExtra("movie_id", dataSet[position].id)
            it.context.startActivity(intent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}