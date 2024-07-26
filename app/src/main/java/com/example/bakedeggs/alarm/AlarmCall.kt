package com.example.bakedeggs.alarm

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AlarmCall(private val context: Context) {

    private lateinit var pendingIntent: PendingIntent

    fun callAlarm(name:String, alarmCode:Long){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("alarm_requestCode",alarmCode.toInt())
            putExtra("alarm_content",name+"에게 연락할 시간입니다.")
        }

        val pendingIntent =
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                PendingIntent.getBroadcast(context, alarmCode.toInt(),receiverIntent,PendingIntent.FLAG_IMMUTABLE)
            else PendingIntent.getBroadcast(context, alarmCode.toInt(),receiverIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        if(Build.VERSION.SDK_INT>=31&&!alarmManager.canScheduleExactAlarms()){
            (context as Activity).requestPermissions(arrayOf(android.Manifest.permission.SCHEDULE_EXACT_ALARM),0)
        }else{
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmCode,pendingIntent)
        }
    }


}