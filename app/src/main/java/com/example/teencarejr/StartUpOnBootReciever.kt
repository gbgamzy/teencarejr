package com.example.teencarejr

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.teencarejr.location.LocationService

class StartUpOnBootReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            Log.w("BOOTUP", "I am running $context")
        Toast.makeText(context,"Boot Up Complete",Toast.LENGTH_LONG).show()
           // val aServiceIntent = Intent(context, LocationService::class.java)
          //  aServiceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        val serviceIntent = Intent("com.example.teencarejr.location.LocationService.BIND")
//        serviceIntent.setPackage("com.example.teencarejr.location")
       // arg0.startService(serviceIntent)
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT)  // Replace 0 with a unique request code
        try {
            pendingIntent.send()
        } catch (e: PendingIntent.CanceledException) {
            e.printStackTrace()
        }
//        context!!.startService(serviceIntent)
       // }
    }
    }
}

//for (appInfo in installedApps) {
////            Log.d("InstalledApp", "Package Name: ${appInfo.packageName}")
//        }