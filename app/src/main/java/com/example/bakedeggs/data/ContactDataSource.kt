package com.example.bakedeggs.data

import android.app.Application

class ContactDataSource(application: Application) {
    companion object{
        @Volatile
        private var INSTANCE: ContactDataSource? = null
        private lateinit var ContactEntities:ArrayList<ContactEntity>


        fun getCacheDataSource(application: Application): ContactDataSource {
            return synchronized(this) {
                val newInstance = INSTANCE ?: ContactDataSource(application)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

    val ContactEntities by lazy { contactList(application) }
}