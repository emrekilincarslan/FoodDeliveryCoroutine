package com.delivery.mobile

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

@BindingAdapter("android:current_status")
fun favourited(view: LottieAnimationView, favourited: Boolean) {
    if (favourited) {
        view.frame = 24
    } else {
        view.frame = 0
    }
}

