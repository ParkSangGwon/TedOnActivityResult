package com.gun0912.tedonactivityresult.coroutine

import android.content.Context
import android.content.Intent
import com.gun0912.tedonactivityresult.ProxyActivity
import com.gun0912.tedonactivityresult.listener.OnActivityResultListener
import com.gun0912.tedonactivityresult.model.ActivityResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object TedOnActivityResult {

    fun with(context: Context): Builder = Builder(context)

    class Builder(private val context: Context) {

        suspend fun startActivityForResult(intent: Intent): ActivityResult =
            suspendCoroutine { continuation ->
                val listener = OnActivityResultListener { resultCode, data ->
                    continuation.resume(ActivityResult(resultCode, data))
                }
                ProxyActivity.startActivityForResult(context, intent, listener)
            }
    }
}
