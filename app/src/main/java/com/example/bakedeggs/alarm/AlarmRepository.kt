package com.example.bakedeggs.alarm

interface AlarmRepository {
    suspend fun addAlarm(alarmEntity: AlarmEntity)
    suspend fun removeAlarm(alarmCode: Long)
}