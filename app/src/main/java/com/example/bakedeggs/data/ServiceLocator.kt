package com.example.bakedeggs.data

import android.app.Application

class ServiceLocator(application: Application) {
    companion object {
        private var INSTANCE: ServiceLocator? = null
        fun getInstance(application: Application): ServiceLocator {
            return synchronized<ServiceLocator>(this) {
                val newInstance = INSTANCE ?: ServiceLocator(application)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

    val contactRepositoryImpl by lazy { ContactRepositoryImpl(ContactDataSource.getContactDataSource(application)) }
}