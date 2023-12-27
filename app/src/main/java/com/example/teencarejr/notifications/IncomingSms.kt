package com.example.teencarejr.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class IncomingSms : BroadcastReceiver() {
    // Get the object of SmsManager
    val sms: SmsManager = SmsManager.getDefault()
    override fun onReceive(context: Context, intent: Intent) {

        // Retrieves a map of extended data from the intent.
        val bundle = intent.extras
        try {
            if (bundle != null) {
                val pdusObj = bundle["pdus"] as Array<Any>?
                for (i in pdusObj!!.indices) {
                    val currentMessage: SmsMessage =
                        SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                    val phoneNumber: String = currentMessage.getDisplayOriginatingAddress()
                    val message: String = currentMessage.getDisplayMessageBody()
                    Log.i("SmsReceiver", "senderNum: $phoneNumber; message: $message")


                    // Show Alert
                    val duration = Toast.LENGTH_LONG
                    //                    Toast toast = Toast.makeText(context,
//                            "senderNum: "+ senderNum + ", message: " + message, duration);
                    //   toast.show();
                    val msgrcv = Intent("Msg")
                    msgrcv.putExtra("package", "")
                    msgrcv.putExtra("ticker", phoneNumber)
                    msgrcv.putExtra("title", phoneNumber)
                    msgrcv.putExtra("text", message)
                    LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv)
                } // end for loop
            } // bundle is null
        } catch (e: Exception) {
            Log.e("SmsReceiver", "Exception smsReceiver$e")
        }
    }
}