package com.example.teencarejr.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class ServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val telephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephony.listen(object : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, incomingNumber: String) {
                super.onCallStateChanged(state, incomingNumber)
                println("incomingNumber : $incomingNumber")
                val msgrcv = Intent("Msg")
                msgrcv.putExtra("package", "")
                msgrcv.putExtra("ticker", incomingNumber)
                msgrcv.putExtra("title", incomingNumber)
                msgrcv.putExtra("text", "")
                LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv)
            }
        }, PhoneStateListener.LISTEN_CALL_STATE)
    }
}