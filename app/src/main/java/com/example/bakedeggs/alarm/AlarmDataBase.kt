package com.example.bakedeggs.alarm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database( entities = [AlarmEntity::class], version = 1)
abstract class AlarmDataBase: RoomDatabase() {
    abstract fun alarmDao(): AlarmDao

    companion object{
        private var INSTANCE: AlarmDataBase? =null
        fun getInstance(context: Context): AlarmDataBase{
            return synchronized(this){
                val newInstance = INSTANCE?: Room.databaseBuilder(context.applicationContext, AlarmDataBase::class.java, "alarm.db").build()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}