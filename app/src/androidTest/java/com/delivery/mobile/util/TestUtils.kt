package com.delivery.mobile.util

import android.app.Activity
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import com.delivery.mobile.restaurants.data.Restaurant
import com.delivery.mobile.restaurants.data.SortingValues
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf

val restaurantName = "Royal Thai"

val testRestaurantA = Restaurant(name ="Tandoori Express",favourited = false,status = "closed",sortingValues = SortingValues(bestMatch = 1.0f,
    newest = 266.0f,ratingAverage = 4.5f,distance = 2308,popularity = 123,averageProductPrice = 1146,deliveryCosts = 150,minCost = 1300) )

val testRestaurantB = Restaurant(name ="Royal Thai",favourited = false,status = "order ahead",sortingValues = SortingValues(bestMatch = 2.0f,
    newest = 133.0f,ratingAverage = 4.5f,distance = 2639,popularity = 44,averageProductPrice = 1492,deliveryCosts = 150,minCost = 2500) )



/**
 * Returns the content description for the navigation button view in the toolbar.
 */
fun getToolbarNavigationContentDescription(activity: Activity, toolbarId: Int) =
    activity.findViewById<Toolbar>(toolbarId).navigationContentDescription as String

/**
 * Simplify testing Intents with Chooser
 *
 * @param matcher the actual intent before wrapped by Chooser Intent
 */
fun chooser(matcher: Matcher<Intent>): Matcher<Intent> = allOf(
    hasAction(Intent.ACTION_CHOOSER),
    hasExtra(`is`(Intent.EXTRA_INTENT), matcher)
)