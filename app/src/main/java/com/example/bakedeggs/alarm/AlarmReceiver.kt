package com.example.bakedeggs.alarm

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.example.bakedeggs.R
import com.example.bakedeggs.main.MainActivity

class AlarmReceiver(): BroadcastReceiver() {

    private lateinit var manager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder

    private val myNotificationID = 1
    private val channelID = "default"

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(context: Context?, intent: Intent?) {
        manager=context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                NotificationChannel(
                    channelID,
                    "default channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }

        builder = NotificationCompat.Builder(context,channelID)

        val intents = Intent(context, MainActivity::class.java)
        val requestCode = intent?.extras!!.getInt("alarm_requestCode")
        val contextText = intent?.extras!!.getString("alarm_content")

        val pendingIntent =
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S)
                PendingIntent.getActivity(context,requestCode,intents,PendingIntent.FLAG_IMMUTABLE)
            else PendingIntent.getActivity(context,requestCode,intents,PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = builder.setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("연락처 알림")
            .setContentText(contextText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            (context as Activity).requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS),0)
            return
        }
        manager.notify(myNotificationID,notification)
    }
}