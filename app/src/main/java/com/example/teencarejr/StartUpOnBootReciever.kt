package com.example.teencarejr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.teencarejr.location.LocationService

class StartUpOnBootReciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent?.action)) {
            Log.w("BOOTUP", "I am running $context")
        Toast.makeText(context,"Boot Up Complete",Toast.LENGTH_LONG).show()
           // val aServiceIntent = Intent(context, LocationService::class.java)
          //  aServiceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val serviceIntent = Intent("com.example.teencarejr.location.LocationService.BIND")
        serviceIntent.setPackage("com.example.teencarejr.location")
       // arg0.startService(serviceIntent)
            context!!.startService(serviceIntent)
       // }
    }
}