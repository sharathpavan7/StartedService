package com.example.startedservice

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
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
        val NOTIFICATION_ID = 100
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
        //Log.i(TAG, text)

        // Intent to launch MainActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // Pending Intent to pass to notification
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Notification builder configuring the notification
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(getString(R.string.question))
            .setContentText(getString(R.string.answer))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 1000))
            .setAutoCancel(true)

        // NOtification manager to issue the notification
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }
}
