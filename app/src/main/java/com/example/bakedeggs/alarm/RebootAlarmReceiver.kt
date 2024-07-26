package com.example.bakedeggs.alarm

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.bakedeggs.alarm.ViewModel.AlarmViewModel
import com.example.bakedeggs.alarm.ViewModel.AlarmViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RebootAlarmReceiver: BroadcastReceiver() {

    private val coroutineScope by lazy { CoroutineScope((Dispatchers.IO)) }

    override fun onReceive(context: Context, intent: Intent) {

        val db = AlarmDataBase.getInstance(context)

        if(intent.action.equals("android.intent.action.BOOT_COMPLETED") ){
            AlarmCall(context).run {
                coroutineScope.launch {
                    val db=AlarmDataBase.getInstance(context)
                    val list=db.alarmDao().getAlarms()
                    if(list.isNotEmpty()){
                        for(i in 0..list.size-1){
                            val name=list[i].name
                            val alarmCode=list[i].alarmCode
                            if(alarmCode>System.currentTimeMillis()) callAlarm(name,alarmCode)
                            db.alarmDao().deleteAlarm(alarmCode)
                            println("${name} / ${alarmCode}")
                        }
                    }else println("Receive: none")
                }
            }
        }
    }

}