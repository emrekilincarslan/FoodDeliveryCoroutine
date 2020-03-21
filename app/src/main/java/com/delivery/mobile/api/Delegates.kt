package com.delivery.mobile.api

import kotlinx.coroutines.*

/**
 * Created by ${Emre}
 */
fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}