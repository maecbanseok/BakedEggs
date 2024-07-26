package com.example.bakedeggs.alarm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakedeggs.alarm.AlarmDataBase
import com.example.bakedeggs.alarm.AlarmEntity
import com.example.bakedeggs.alarm.AlarmRepository
import com.example.bakedeggs.alarm.AlarmRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application):AndroidViewModel(application) {

    private val alarmRepositoryImpl =AlarmRepositoryImpl(AlarmDataBase.getInstance(application))

    fun addAlarm(alarmEntity: AlarmEntity){
        CoroutineScope(Dispatchers.IO).launch {
            alarmRepositoryImpl.addAlarm(alarmEntity)
        }
    }

    fun removeAlarm(alarmCode: Long){
        CoroutineScope(Dispatchers.IO).launch {
            alarmRepositoryImpl.removeAlarm(alarmCode)
        }
    }
}