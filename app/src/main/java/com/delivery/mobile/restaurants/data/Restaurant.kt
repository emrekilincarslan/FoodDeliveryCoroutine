package com.delivery.mobile.restaurants.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant(
        @PrimaryKey()
        val name: String,
        val status: String,
        var favourited:Boolean,
        @Embedded(prefix = "restaurants_")
        val sortingValues:SortingValues?=null

) {
    override fun toString() = name
}