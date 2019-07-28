package com.android.freelance.famousplaces.internal

import android.util.Log
import kotlinx.coroutines.*

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    val LOG_TAG = "Delegates.kt"

    return lazy {
        Log.i(LOG_TAG, "TEST: lazyDeferred() called...")

        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}