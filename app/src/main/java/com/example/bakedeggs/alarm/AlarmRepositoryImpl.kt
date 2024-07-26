package com.example.bakedeggs.alarm

import android.app.Application
import android.content.Context

class AlarmRepositoryImpl(private val db: AlarmDataBase):AlarmRepository {

    override suspend fun addAlarm(alarmEntity: AlarmEntity) {
        db.alarmDao().addAlarm(alarmEntity)
    }

    override suspend fun removeAlarm(alarmCode: Long) {
        db.alarmDao().deleteAlarm(alarmCode)
    }
}