package com.example.startedservice

import android.app.IntentService
import android.content.Intent
import android.util.Log
import java.lang.Exception

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions and extra parameters.
 */
class DelayedMessageService : IntentService("DelayedMessageService") {

    companion object {
        val MESSAGE: String = "message"
    }

    val TAG: String = "DelayedMessageService"

    override fun onHandleIntent(intent: Intent?) {
        synchronized(this) {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {}
        }
        showText(intent!!.extras.getString(MESSAGE))
    }

    fun showText(text: String) {
        Log.i(TAG, text)
    }
}
