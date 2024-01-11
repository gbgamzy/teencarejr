package com.example.teencarejr

import android.app.AppOpsManager
import android.app.usage.UsageStatsManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.teencarejr.location.LocationService
import com.example.teencarejr.notifications.Model
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_USAGE_STATS_PERMISSION = 1
    }

    var modelList: ArrayList<Model>? = null
    private val locationPermissionCode = 123
    private val notificationPermissionCode = 456

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        startActivity(intent)
        // Permission already granted, proceed with app usage data retrieval
        // ... (your code to access usage data here)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -1) // Use data from the last 24 hours
        val startTime = calendar.timeInMillis
        val endTime = System.currentTimeMillis()
        val usageStatsManager =
            getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        // Last hour
        val usageStats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        )
        for (usageStat in usageStats) {
            val packageName = usageStat.packageName
            var lastTimeUsed = usageStat.lastTimeUsed
            lastTimeUsed /= (1000 * 60)
            val totalTimeInForeground = usageStat.totalTimeInForeground/(1000*60)
            Log.d("Usage", "$packageName : $lastTimeUsed $totalTimeInForeground")
        }

       // LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, IntentFilter("Msg"));



//        val fineLocationPermission =
//            ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            )
//        val coarseLocationPermission =
//            ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            )
//
//        if (fineLocationPermission == PackageManager.PERMISSION_GRANTED &&
//            coarseLocationPermission == PackageManager.PERMISSION_GRANTED
//        ) {
//            listenNotifications()
//            Intent(applicationContext, LocationService::class.java).apply {
//                action = LocationService.ACTION_START
//                startForegroundService(this)
//            }
//
//        } else {
//            // Request location permissions
//            requestPermissions()
//        }

//        findViewById<Button>(R.id.stopBtn).setOnClickListener {
//            Intent(applicationContext, LocationService::class.java).apply {
//                action = LocationService.ACTION_STOP
//                startService(this)
//            }
//        }
    }

//    private fun listenNotifications() {
//
//        Toast.makeText(
//            this,
//            "Please grant the permission to access notifications",
//            Toast.LENGTH_LONG
//        ).show()
//        LocalBroadcastManager.getInstance(this)
//            .registerReceiver(onNotice, IntentFilter("Msg"));
//        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        if (Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
//                .contains(applicationContext.packageName)
//        ) {
////service is enabled do something
//        } else {
////service is not enabled try to enabled by calling...
//            applicationContext.startActivity(intent);
//        }
//    }
//
//    private val onNotice: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            // String pack = intent.getStringExtra("package");
//            // String pack = intent.getStringExtra("package");
//            val title = intent!!.getStringExtra("title")
//            val text = intent.getStringExtra("text")
//            //int id = intent.getIntExtra("icon",0);
//
//            //int id = intent.getIntExtra("icon",0);
//            val remotePackageContext: Context? = null
//            try {
////                remotePackageContext = getApplicationContext().createPackageContext(pack, 0);
////                Drawable icon = remotePackageContext.getResources().getDrawable(id);
////                if(icon !=null) {
////                    ((ImageView) findViewById(R.id.imageView)).setBackground(icon);
////                }
//                val byteArray = intent.getByteArrayExtra("icon")
//                var bmp: Bitmap? = null
//                if (byteArray != null) {
//                    bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
//                }
//                val model = Model()
//                model.name = "$title $text"
//                model.image = bmp
//                Log.d("Notification", "Title ='$title' && Text = '$text' ")
//                if (modelList != null) {
//                    modelList!!.add(model)
//                } else {
//                    modelList = ArrayList()
//                    modelList!!.add(model)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//    }
//
//    private fun requestPermissions() {
//        // Request fine and coarse location permissions
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(
//                android.Manifest.permission.ACCESS_FINE_LOCATION,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ),
//
//            locationPermissionCode
//        )
//    }
//
//    // Handle the result of the permission request
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            locationPermissionCode -> {
//                if (grantResults.isNotEmpty() &&
//                    grantResults.all { it == PackageManager.PERMISSION_GRANTED }
//                ) {
//                    listenNotifications()
//                    Intent(applicationContext, LocationService::class.java).apply {
//                        action = LocationService.ACTION_START
//                        startForegroundService(this)
//                    }
//                    // Permissions granted, do your location-related task here.
//                    // Example: getLocation()
//                } else {
//                    Toast.makeText(this, "Please Grant the permission location", Toast.LENGTH_LONG)
//                        .show()
//                    requestPermissions()
//                }
//            }
//        }
//    }
}
