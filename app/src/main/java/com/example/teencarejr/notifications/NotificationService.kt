package com.example.teencarejr.notifications

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.ByteArrayOutputStream


class NotificationService : NotificationListenerService() {
    var context: Context? = null
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        //Log.d("Notificaton","$activeNotifications")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val pack = sbn.packageName
        var ticker = ""
        if (sbn.notification.tickerText != null) {
            ticker = sbn.notification.tickerText.toString()
        }
        val extras = sbn.notification.extras
        val title = extras.getString("android.title")
        var text: String? = null

        if (extras.getCharSequence("android.text") != null) {
            text = extras.getCharSequence("android.text").toString()
        }
        if (text == null) {
// for whatsapp on kitkat second whats app text
// will be null
            if (extras["android.textLines"] != null) {
                val charText = extras["android.textLines"] as Array<CharSequence>?
                if (charText!!.size > 0) {
                    text = charText!![charText!!.size - 1].toString()
                }
            }
        }


        //val text = extras.getCharSequence("android.text").toString()
        val id1 = extras.getInt(Notification.EXTRA_SMALL_ICON)
        val id = sbn.notification.largeIcon
        Log.i("Package", pack)
        Log.i("Ticker", ticker)
        Log.i("Title", title.toString())
        Log.i("Text", text.toString())
        val msgrcv = Intent("Msg")
        msgrcv.putExtra("package", pack)
        msgrcv.putExtra("ticker", ticker)
        msgrcv.putExtra("title", title)
        msgrcv.putExtra("text", text)
        if (id != null) {
            val stream = ByteArrayOutputStream()
            id.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            msgrcv.putExtra("icon", byteArray)
        }
        LocalBroadcastManager.getInstance(context!!).sendBroadcast(msgrcv)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.i("Msg", "Notification Removed")
    }
}