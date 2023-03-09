package com.zenika.zenilunch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val restaurants: List<RestaurantUIModel>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val RestaurantUIModel = restaurants[position]
        //holder.id.text = RestaurantUIModel.id.toString()
        holder.nom.text = RestaurantUIModel.nom
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val id: TextView = itemView.findViewById(R.id.id)
        val nom: TextView = itemView.findViewById(R.id.nom)
    }
}