package com.zenika.zenilunch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object RestaurantDiff : DiffUtil.ItemCallback<RestaurantUIModel>() {
    override fun areItemsTheSame(oldItem: RestaurantUIModel, newItem: RestaurantUIModel): Boolean {
        return (oldItem.name == newItem.name)
    }

    override fun areContentsTheSame(
        oldItem: RestaurantUIModel,
        newItem: RestaurantUIModel
    ): Boolean {
        return (oldItem.name == newItem.name)
    }
}

class RestaurantAdapter(
    private val onItemClick: (RestaurantUIModel) -> Unit
) : ListAdapter<RestaurantUIModel, RestaurantAdapter.ViewHolder>(RestaurantDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurantUIModel = getItem(position)
        holder.bind(restaurantUIModel, onItemClick)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.restaurant_name)

        fun bind(
            restaurantUIModel: RestaurantUIModel,
            onClick: (RestaurantUIModel) -> Unit
        ) {
            name.text = restaurantUIModel.name
            itemView.setOnClickListener {
                onClick(restaurantUIModel)
            }
        }
    }
}