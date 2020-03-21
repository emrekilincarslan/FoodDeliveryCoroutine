package com.delivery.mobile.restaurants.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.delivery.mobile.databinding.ListItemRestaurantBinding
import com.delivery.mobile.restaurants.data.Restaurant

/**
 * Adapter for the [RecyclerView] in [RestaurantsFragment].
 */
class RestaurantsAdapter(var restaurantsFragment: RestaurantsFragment):
    ListAdapter<Restaurant, RestaurantsAdapter.ViewHolder>(DiffCallback()) {


    override fun onBindViewHolder( holder: ViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.apply {
            bind(
                createOnClickListener(restaurant), restaurant
            )
            itemView.tag = restaurant

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(restaurantsFragment,
            ListItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(restaurant: Restaurant): View.OnClickListener {
        return View.OnClickListener {
            Log.d("fdfdehd", "clicked $restaurant")
        }
    }

    class ViewHolder(
        private val restaurantsFragment: RestaurantsFragment,
        private val binding: ListItemRestaurantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Restaurant) {
            binding.apply {
                clickListener = listener
                restaurant = item
                fragment = restaurantsFragment
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Restaurant>() {

    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.name == newItem.name && oldItem.favourited==newItem.favourited
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}