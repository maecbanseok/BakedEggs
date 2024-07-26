package com.example.bakedeggs.alarm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlarmDao {
    @Query("select * from alarms")
    fun getAlarms() : List<AlarmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlarm(item: AlarmEntity)

    @Query("DELETE FROM alarms WHERE alarmCode = :alarmCode")
    fun deleteAlarm(alarmCode: Long)
}