package com.example.bakedeggs.data

import android.app.Application

class ServiceLocator(application: Application) {
    companion object{
        private var INSTANCE:ServiceLocator? = null
        fun getInstance(application: Application) {
            return synchronized<Unit>(this) {
                val newInstance = INSTANCE ?: ServiceLocator(application)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
    val contactDataSource = ContactDataSource.getCacheDataSource(application)
}