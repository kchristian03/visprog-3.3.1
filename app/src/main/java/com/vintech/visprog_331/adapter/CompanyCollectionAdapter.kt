package com.vintech.visprog_331.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vintech.visprog_331.R
import com.vintech.visprog_331.helper.Const
import com.vintech.visprog_331.model.ProductionCompany


class CompanyCollectionAdapter(private val dataSet: List<ProductionCompany>) :
    RecyclerView.Adapter<CompanyCollectionAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img_company: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            img_company = view.findViewById(R.id.img_company)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_company_collection, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Glide.with(viewHolder.itemView).load(Const.IMG_URL + dataSet[position].logo_path).into(viewHolder.img_company)
//        viewHolder.img_company.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

