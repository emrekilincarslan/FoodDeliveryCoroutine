package com.delivery.mobile.ui

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.delivery.mobile.restaurants.data.Restaurant
import com.delivery.mobile.ui.Creteria.*

fun Fragment.setTitle(title: String) {
    (activity as AppCompatActivity).supportActionBar!!.title = title
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

enum class Creteria {
    newest,
    rating,
    distance,
    popularity,
    avarage,
    deliverycost,
    mincost
}

fun List<Restaurant>.customSort(creteria: Creteria): List<Restaurant> {
    return when (creteria) {
        newest -> this.sortedWith(compareBy<Restaurant>
        {
            it.favourited.equals(true)
        }
            .thenBy { it.status.equals("open") }
            .thenBy { it.status.equals("order ahead") }
            .thenBy { it.sortingValues?.newest }).reversed()

        rating -> this.sortedWith(compareBy<Restaurant>
        {
            it.favourited.equals(true)
        }
            .thenBy { it.status.equals("open") }
            .thenBy { it.status.equals("order ahead") }
            .thenBy { it.sortingValues?.ratingAverage }).reversed()

        distance -> this.sortedWith(compareBy<Restaurant>
        {
            it.favourited.equals(true)
        }
            .thenBy { it.status.equals("open") }
            .thenBy { it.status.equals("order ahead") }
            .thenBy { it.sortingValues?.distance }).reversed()

        popularity -> this.sortedWith(compareBy<Restaurant>
        {
            it.favourited.equals(true)
        }
            .thenBy { it.status.equals("open") }
            .thenBy { it.status.equals("order ahead") }
            .thenBy { it.sortingValues?.popularity }).reversed()

        avarage -> this.sortedWith(compareBy<Restaurant>
        {
            it.favourited.equals(true)
        }
            .thenBy { it.status.equals("open") }
            .thenBy { it.status.equals("order ahead") }
            .thenBy { it.sortingValues?.averageProductPrice }).reversed()

        deliverycost -> this.sortedWith(compareBy<Restaurant>
        {
            it.favourited.equals(true)
        }
            .thenBy { it.status.equals("open") }
            .thenBy { it.status.equals("order ahead") }
            .thenBy { it.sortingValues?.deliveryCosts }).reversed()

        mincost -> this.sortedWith(compareBy<Restaurant>
        {
            it.favourited.equals(true)
        }
            .thenBy { it.status.equals("open") }
            .thenBy { it.status.equals("order ahead") }
            .thenBy { it.sortingValues?.minCost }).reversed()

    }


}

